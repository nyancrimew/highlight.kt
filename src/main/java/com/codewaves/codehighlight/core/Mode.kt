package com.codewaves.codehighlight.core
import java.util.regex.Pattern
/**
* Created by Sergej Kravcenko on 5/12/2017.
* Copyright (c) 2017 Sergej Kravcenko
*/
data class Mode(
   // Language
    val aliases: List<String> = emptyList(),
    val case_insensitive: Boolean = false,
   // Mode
    val self: Boolean = false,
    val subLanguage: String? = null,
    val subLanguages: List<String> = emptyList(),
    val starts: Mode? = null,
    var contains: List<Mode> = emptyList(),
    val variants: List<Mode> = emptyList(),
    val beginKeywords: List<Keyword> = emptyList(),
    val keywords: List<Keyword> = emptyList(),
    val className: String? = null,
    var begin: String? = null,
    var end: String? = null,
    val lexemes: String? = null,
    var terminators: Pattern? = null,
    val illegal: String? = null,
    var terminatorEnd: String? = null,
    var relevance: Int = -1,
    val skip: Boolean = false,
    val returnBegin: Boolean = false,
    val excludeBegin: Boolean = false,
    val returnEnd: Boolean = false,
    val excludeEnd: Boolean = false,
    val endsWithParent: Boolean = false,
    val endsParent: Boolean = false
) {
  internal var compiled:Boolean = false
  internal var compiledKeywords = mapOf<String, Keyword>()
  internal var beginRe:Pattern? = null
  internal var endRe : Pattern? = null
  internal var lexemesRe : Pattern? = null
  internal var illegalRe : Pattern? = null

  private fun langRe(re: String) = "(?m" + (if(case_insensitive) "i" else "") + ")" + re

  private fun expandMode(mode: Mode) = if (mode.variants.isNotEmpty()) {
         mode.variants.map { inherit(mode, it) }
     } else if (mode.endsWithParent) {
        listOf(inherit(mode, null))
     } else listOf(mode)

   private fun compileMode(mode: Mode, parent: Mode?) {
      if (mode.compiled) {
         return
      }
      mode.compiled = true

      // Keywords
      val keywords = if(mode.keywords.isEmpty()) mode.beginKeywords else mode.keywords
      if (keywords.isNotEmpty()) {
         mode.compiledKeywords = keywords.flatMap { group ->
            val words = if (case_insensitive) group.value.toLowerCase() else group.value
            words.split(" ").map { word ->
               val pair = word.split("\\|")
               val relevance = if (pair.size > 1) pair[1].toInt() else 1
               pair[0] to Keyword(pair[0], group.className, relevance)
            }
         }.toMap()
      }

      // Lexemes
      mode.lexemesRe = if (mode.lexemes == null) emptyLexemePattern else Pattern.compile(langRe(mode.lexemes))
      
      // Parent
      if (parent != null) {
         if (mode.beginKeywords.isNotEmpty()) {
            mode.begin = "\\b(" + mode.beginKeywords[0].value.split(" ").joinToString("|") + ")\\b"
         }
         if (mode.begin == null) {
            mode.begin = "\\B|\\b"
            mode.beginRe = emptyBeginEndPattern
         } else {
            mode.beginRe = Pattern.compile(langRe(mode.begin!!))
         }
         
         if(mode.end == null && !mode.endsWithParent) {
            mode.end = "\\B|\\b"
            mode.endRe = emptyBeginEndPattern
         } else if (!mode.end.isNullOrEmpty()) {
            mode.endRe = Pattern.compile(langRe(mode.end as String))
         }

         mode.terminatorEnd = mode.end ?: ""
         if (mode.endsWithParent && parent.terminatorEnd != null) {
            mode.terminatorEnd += (if (mode.end == null) "" else "|") + parent.terminatorEnd
         }
      }

      // Relevance
      if (mode.relevance == -1) {
         mode.relevance = 1;
      }

      // Illegal
      if (mode.illegal != null) {
         mode.illegalRe = Pattern.compile(langRe(mode.illegal))
      }

      // Contains
      mode.contains = mode.contains.flatMap {
         expandMode(if(it.self) mode else it)
      }
      for (child in mode.contains) {
         compileMode(child, mode)
      }

      // Starts
      if (mode.starts != null) {
         compileMode(mode.starts, parent)
      }

      // Terminators
      for (child in mode.contains) {

      }
      val terminators = mode.contains.mapNotNull {
         if (it.beginKeywords.isNotEmpty()) "\\.?(" + it.begin + ")\\.?" else it.begin
      }.toMutableList()
      if (!mode.terminatorEnd.isNullOrEmpty()) {
         terminators += mode.terminatorEnd!!
      }
      if (!mode.illegal.isNullOrEmpty()) {
         terminators += mode.illegal
      }
      mode.terminators = if (terminators.isNotEmpty()) Pattern.compile(langRe(terminators.joinToString("|"))) else null
   }

   fun compile() {
      compileMode(this, null)
   }

  companion object {
     private val emptyLexemePattern = Pattern.compile("\\w+", Pattern.MULTILINE)
     private val emptyBeginEndPattern = Pattern.compile("\\B|\\b", Pattern.MULTILINE)
    val SELF = Mode(self = true)
    val IDENT_RE = "[a-zA-Z]\\w*"
    val UNDERSCORE_IDENT_RE = "[a-zA-Z_]\\w*"
    val NUMBER_RE = "\\b\\d+(\\.\\d+)?"
    val C_NUMBER_RE = "(-?)(\\b0[xX][a-fA-F0-9]+|(\\b\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?)" // 0x..., 0..., decimal, float
    val BINARY_NUMBER_RE = "\\b(0b[01]+)" // 0b...
    val RE_STARTERS_RE = "!|!=|!==|%|%=|&|&&|&=|\\*|\\*=|\\+|\\+=|,|-|-=|/=|/|:|;|<<|<<=|<=|<|===|==|=|>>>=|>>=|>=|>>>|>>|>|\\?|\\[|\\{|\\(|\\^|\\^=|\\||\\|=|\\|\\||~"
    val BACKSLASH_ESCAPE = Mode(begin = "\\\\[\\s\\S]", relevance = 0)
    val APOS_STRING_MODE = Mode(className = "string", begin= "\'", end = "\'", illegal= "\\n", contains = listOf(BACKSLASH_ESCAPE))
    val QUOTE_STRING_MODE = Mode(className = "string", begin= "\"", end= "\"", illegal= "\\n", contains= listOf(BACKSLASH_ESCAPE))
    val PHRASAL_WORDS_MODE = Mode(begin = "\\b(a|an|the|are|I'm|isn't|don't|doesn't|won't|but|just|should|pretty|simply|enough|gonna|going|wtf|so|such|will|you|your|they|like|more)\\b")
    fun COMMENT(begin:String, end:String? = null, inherits:Mode? = null) : Mode {
       var mode = Mode(
          className = "comment",
          begin = begin,
          end = end
       )
       if (inherits != null) {
          mode = inherit(mode, inherits)
       }
      return mode.copy(
         contains = mode.contains + listOf(
            PHRASAL_WORDS_MODE,
            Mode(
               className = "doctag",
               begin = "(?:TODO|FIXME|NOTE|BUG|XXX):",
               relevance = 0
            )
         )
      )
    }
    val C_LINE_COMMENT_MODE = COMMENT("//", "$", null)
    val C_BLOCK_COMMENT_MODE = COMMENT("/\\*", "\\*/", null)
    val HASH_COMMENT_MODE = COMMENT("#", "$", null)
    val NUMBER_MODE = Mode(className = "number", begin = NUMBER_RE, relevance = 0)
    val C_NUMBER_MODE = Mode(className = "number", begin = C_NUMBER_RE, relevance = 0)
    val BINARY_NUMBER_MODE = Mode(className = "number", begin = BINARY_NUMBER_RE, relevance = 0)
    val TITLE_MODE = Mode(className = "title", begin = IDENT_RE, relevance = 0)
    val UNDERSCORE_TITLE_MODE = Mode(className = "title", begin = UNDERSCORE_IDENT_RE, relevance = 0)
    val METHOD_GUARD = Mode(begin = "\\.\\s*" + UNDERSCORE_IDENT_RE, relevance = 0)
    val CSS_NUMBER_MODE = Mode(className = "number", begin =(Mode.NUMBER_RE + "(" +
                                                            "%|em|ex|ch|rem" +
                                                            "|vw|vh|vmin|vmax" +
                                                            "|cm|mm|in|pt|pc|px" +
                                                            "|deg|grad|rad|turn" +
                                                            "|s|ms" +
                                                            "|Hz|kHz" +
                                                            "|dpi|dpcm|dppx" +
                                                            ")?"), relevance = 0)
    val REGEXP_MODE = Mode(className = "regexp", begin = "\\/", end = "\\/[gimuy]*", illegal = "\\n", contains = listOf(BACKSLASH_ESCAPE, Mode(begin = "\\[", end = "\\]", relevance = 0, contains = listOf(BACKSLASH_ESCAPE))))

    fun inherit(mode:Mode, from:Mode? = null) = if (from == null) mode else mode.copy(
          starts = from.starts ?: mode.starts,
          contains = if (from.contains.isNotEmpty()) from.contains else mode.contains,
          variants = if (from.variants.isNotEmpty()) from.variants else mode.variants,
          className = from.className ?: mode.className,
          keywords = if (from.keywords.isNotEmpty()) from.keywords else mode.keywords,
          beginKeywords = if (from.beginKeywords.isNotEmpty()) from.beginKeywords else mode.beginKeywords,
          begin = from.begin ?: mode.begin,
          end = from.end ?: mode.end,
          illegal = from.illegal ?: mode.illegal,
          relevance = if (from.relevance != -1) from.relevance else mode.relevance,
          returnBegin = from.returnBegin || mode.returnBegin,
          excludeBegin = from.excludeBegin || mode.excludeBegin,
          returnEnd = from.returnEnd || mode.returnEnd,
          excludeEnd = from.excludeEnd || mode.excludeEnd,
          endsWithParent = from.endsWithParent || mode.endsWithParent,
          endsParent = from.endsParent || mode.endsParent
       )
   }
}