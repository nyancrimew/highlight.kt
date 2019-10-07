package com.codewaves.codehighlight.core;

import com.codewaves.codehighlight.languages.*;

/*
 * Created by Sergej Kravcenko on 5/17/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

/**
 * Main class for code syntax highlighting. Contains all supported languages
 * and two main methods. Use {@link Highlighter#highlight(String, String)} if
 * code language is known or use {@link Highlighter#highlightAuto(String, String[])}
 * to automatically detect code language.
 */
class Highlighter(val rendererFactory: StyleRendererFactory) {
   companion object {
      internal val languageMap = mutableMapOf<String, Mode>()
      internal val languages = mutableSetOf<String>()
      
      init {
         registerLanguage("java", JavaLanguage().build())
      }

      @JvmStatic
      fun findLanguage(name: String) = languageMap[name]
      
      @JvmStatic
      private fun registerLanguage(name: String, language: Mode) {
         languages += name
         languageMap += name to language
         languageMap += language.aliases.associateWith { language }
      }
   }

   /**
    * Core highlighting function. Accepts a language name, or an alias, and a
    * string with the code to highlight.
    *
    * @param languageName language name
    * @param code code string to highlight
    *
    * @return the given code highlight result
    */
   fun highlight(languageName: String, code: String): HighlightResult {
      // Find Language
      val language = findLanguage(languageName) ?: return HighlightResult(0, null, code)

      // Parse
      val renderer = rendererFactory.create(languageName)
      val parser = HighlightParser(language, rendererFactory, renderer)
      val relevance = parser.highlight(code, false, null)
      return HighlightResult(relevance, languageName, renderer.getResult())
   }

   /**
    * Highlighting with language detection. Accepts a string with the code to
    * highlight.
    *
    * @param code code string to highlight
    * @param languageSubset list of languages for checking or empty list for all known languages
    *
    * @return the given code highlight result
    */
    fun highlightAuto(code: String, languageSubset: List<String>? = null): HighlightResult {
      val langs = if(languageSubset.isNullOrEmpty()) languages else languageSubset

      var bestRelevance = -1
      var bestLanguageName: String? = null
      var result: CharSequence? = null
      for (languageName in langs) {
         val language = findLanguage(languageName) ?: continue

         val renderer = rendererFactory.create(languageName)
         val parser = HighlightParser(language, rendererFactory, renderer)
         val relevance = parser.highlight(code, false, null)
         if (relevance > bestRelevance) {
            bestRelevance = relevance
            bestLanguageName = languageName
            result = renderer.getResult()
         }
      }

      return HighlightResult(bestRelevance, bestLanguageName, result ?: code)
    }
    
    /**
     * Result of code syntax highlighting
     */
    data class HighlightResult(
       val relevance: Int,
       val language: String?,
       val result: CharSequence
    )
}
