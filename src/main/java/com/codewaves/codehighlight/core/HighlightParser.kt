package com.codewaves.codehighlight.core;

import java.util.regex.Pattern;

/**
 * Created by Sergej Kravcenko on 5/13/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */
class HighlightParser(
   val language: Mode,
   val rendererFactory: StyleRendererFactory,
   val blockRenderer: StyleRenderer
) {

   private var modeBuffer: String = ""
   private lateinit var top: ParentWrapper
   private val continuations = mutableMapOf<String, ParentWrapper>()
   private var ignoreIllegals = false
   private var relevance = 0

   data class ParentWrapper(
      val mode: Mode,
      val parent: ParentWrapper?
   )

   private fun testRe(re: Pattern?, lexeme: String): Boolean {
      if (re == null) {
         return false
      }
      val matcher = re.matcher(lexeme)
      return matcher.find() && matcher.start() == 0
   }

   private fun subMode(lexeme: String, mode: Mode) = mode.contains.firstOrNull { testRe(it.beginRe, lexeme) }

   private fun startNewMode(mode: Mode) {
      if (mode.className != null) {
         blockRenderer.onPushStyle(mode.className)
      }
      top = ParentWrapper(mode, top)
   }

   // TODO: tail recursion?
   private fun endOfMode(mode: ParentWrapper?, lexeme: String): ParentWrapper? {
      var iterMode = mode ?: return null
      if (testRe(iterMode.mode.endRe, lexeme)) {
         while(iterMode.mode.endsParent && iterMode.parent != null) {
            iterMode = iterMode.parent as ParentWrapper
         }
         return iterMode
      }
      if (iterMode.mode.endsWithParent) {
         return endOfMode(iterMode.parent, lexeme)
      }
      return null
   }

   private fun processBuffer() {
      if (top.mode.subLanguage != null || top.mode.subLanguages.isNotEmpty()) {
         processSubLanguage()
      } else {
         processKeywords()
      }
      modeBuffer = ""
   }

   private fun isIllegal(lexeme: String, mode: Mode) = !ignoreIllegals && testRe(mode.illegalRe, lexeme)

   private fun keywordMatch(mode: Mode, match: String) = mode.compiledKeywords.get(if(language.case_insensitive) match.toLowerCase() else match)

   private fun processKeywords() {
      if (top.mode.compiledKeywords.isEmpty()) {
         blockRenderer.onPushCodeBlock(modeBuffer)
         return
      }

      var lastIndex = 0
      val matcher = top.mode.lexemesRe!!.matcher(modeBuffer)
      while (matcher.find()) {
         blockRenderer.onPushCodeBlock(modeBuffer.substring(lastIndex, matcher.start()))
         val keyword = keywordMatch(top.mode, matcher.group())
         if (keyword != null) {
            relevance += keyword.relevance
            blockRenderer.onPushStyle(keyword.className)
            blockRenderer.onPushCodeBlock(matcher.group())
            blockRenderer.onPopStyle()
         } else {
            blockRenderer.onPushCodeBlock(matcher.group())
         }
         lastIndex = matcher.end()
      }
      blockRenderer.onPushCodeBlock(modeBuffer.substring(lastIndex))
   }

   private fun processSubLanguage() {
      val explicit = top.mode.subLanguage != null
      var relevance = 0
      var resultCode: CharSequence? = null
      var resultLanguage: String? = null

      if (explicit) {
         val languageName = top.mode.subLanguage!!
         val language = Highlighter.findLanguage(languageName)
         if (language == null) {
            blockRenderer.onPushSubLanguage(null, modeBuffer)
            return
         }

         val renderer = rendererFactory.create(languageName)
         val parser = HighlightParser(language, rendererFactory, renderer)
         relevance = parser.highlight(modeBuffer, true, continuations.get(languageName))
         resultLanguage = languageName
         continuations.put(languageName, parser.top)
      } else {
         val highlighter = Highlighter(rendererFactory)
         val result = highlighter.highlightAuto(modeBuffer, top.mode.subLanguages)
         relevance = result.relevance
         resultCode = result.result
         resultLanguage = result.language
      }

      
      // Counting embedded language score towards the host language may be disabled
      // with zeroing the containing mode relevance. Usecase in point is Markdown that
      // allows XML everywhere and makes every XML snippet to have a much larger Markdown
      // score.
      if (top.mode.relevance > 0) {
         this.relevance += relevance
      }
      blockRenderer.onPushSubLanguage(resultLanguage, resultCode)
   }

   private fun processLexeme(buffer: String, lexeme: String?): Int {
      modeBuffer += buffer

      if (lexeme == null) {
         processBuffer()
         return 0
      }

      val newMode = subMode(lexeme, top.mode)
      if (newMode != null) {
         if (newMode.skip) {
            modeBuffer += lexeme
         } else {
            if (newMode.excludeBegin) {
               modeBuffer += lexeme
            }
            processBuffer()
            if (!newMode.returnBegin && !newMode.excludeBegin) {
               modeBuffer = lexeme
            }
         }
         startNewMode(newMode)
         return if (newMode.returnBegin) 0 else lexeme.length
      }

      val endMode = endOfMode(top, lexeme)
      if (endMode != null) {
         val origin = top.mode
         if (origin.skip) {
            modeBuffer += lexeme
         } else {
            if (!(origin.returnEnd || origin.excludeEnd)) {
               modeBuffer += lexeme
            }
            processBuffer()
            if (origin.excludeEnd) {
               modeBuffer += lexeme
            }
         }

         do {
            if (top.mode.className != null) {
               blockRenderer.onPopStyle()
            }
            if (!top.mode.skip && top.mode.subLanguage == null) {
               relevance += top.mode.relevance
            }
            top = top.parent!!
         } while (top != endMode.parent)

         if (endMode.mode.starts != null) {
            startNewMode(endMode.mode.starts)
         }
         return if (origin.returnEnd) 0 else lexeme.length
      }

      if (isIllegal(lexeme, top.mode)) {
         throw Exception("Illegal lexeme \"$lexeme\" for mode \"${top.mode.className}\"")
      }

      /*
       * Parser should not reach this point as all types of lexemes should be caught
       * earlier, but if it does due to some bug make sure it advances at least one
       * character forward to prevent infinite looping.
       */
      modeBuffer += lexeme
      return Math.max(lexeme.length, 1)
   }

   fun highlight(code: String, ignoreIllegals: Boolean, continuation: ParentWrapper?): Int {
      try {
         blockRenderer.onStart()
         language.compile()
         modeBuffer = ""
         this.ignoreIllegals = ignoreIllegals
         relevance = 0
         top = continuation ?: ParentWrapper(language, null)
         continuations.clear()

         var current: ParentWrapper? = top
         val stack = mutableListOf<String>()
         while (current != null && current.mode != language) {
            if (current.mode.className != null) {
               stack.add(0, current.mode.className as String)
            }
            current = current.parent
         }
         
         for(className in stack) {
            blockRenderer.onPushStyle(className)
         }

         var index = 0
         while (top.mode.terminators != null) {
            val matcher = (top.mode.terminators as Pattern).matcher(code)
            if (!matcher.find(index)) break

            val count = processLexeme(code.substring(index, matcher.start()), matcher.group())
            index = matcher.start() + count  
         }
         processLexeme(code.substring(index), null)

         current = top
         while (current != null && current.mode != language) {
            if (current.mode.className != null) {
               blockRenderer.onPopStyle()
            }
            current = current.parent
         }
         blockRenderer.onFinish()
      } catch(e: Exception) {
         blockRenderer.onAbort(code)
         relevance = 0
      }
      
      return relevance
   }
}
