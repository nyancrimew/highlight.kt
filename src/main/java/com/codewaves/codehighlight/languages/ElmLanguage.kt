package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Elm
Author = Janis Voigtlaender <janis.voigtlaender@gmail.com>
Category = functional
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ElmLanguage : LanguageBuilder  {
  val COMMENT = Mode(
    variants = listOf(
      hljs.COMMENT("--",
 "\$"),
      hljs.COMMENT(
        "{-",

        "-}",

        Mode(
          contains = listOf("self")
        )
      )
    )
  );

  val CONSTRUCTOR = Mode(
    className = "type",

    begin = "\\b[A-Z][\\w\"]*"
 // TODO: other constructors (built-in, infix).
    relevance = 0
  );

  val LIST = Mode(
    begin = "\\(",

 end = "\\)",

    illegal = "\"",

    contains = listOf(
      Mode(className = "type",

 begin = "\\b[A-Z][\\w]*(\\((\\.\\.|,|\\w+)\\))?"),
      COMMENT
    )
  );

  val RECORD = Mode(
    begin = "{",

 end = "}",

    contains = LIST.contains
  );

  val CHARACTER = Mode(
    className = "string",

    begin = "\'\\\\?.",

 end = "\'",

    illegal = "."
  );

  override fun build() = Mode(
    keywords =       keywords("let in if then else case of where module import exposing type alias as infix infixl infixr port effect command subscription"),
    contains = listOf(

      // Top-level constructions.

      Mode(
        beginKeywords = keywords("port effect module",

 end = "exposing"),
        keywords = keywords("port effect module where command subscription exposing"),
        contains = listOf(LIST, COMMENT),
        illegal = "\\W\\.|;"
      ),
      Mode(
        begin = "import",

 end = "\$",

        keywords = keywords("import as exposing"),
        contains = listOf(LIST, COMMENT),
        illegal = "\\W\\.|;"
      ),
      Mode(
        begin = "type",

 end = "\$",

        keywords = keywords("type alias"),
        contains = listOf(CONSTRUCTOR, LIST, RECORD, COMMENT)
      ),
      Mode(
        beginKeywords = keywords("infix infixl infixr",

 end = "\$"),
        contains = listOf(hljs.C_NUMBER_MODE, COMMENT)
      ),
      Mode(
        begin = "port",

 end = "\$",

        keywords = keywords("port"),
        contains = listOf(COMMENT)
      ),

      // Literals and names.

      CHARACTER,
      hljs.QUOTE_STRING_MODE,
      hljs.C_NUMBER_MODE,
      CONSTRUCTOR,
      hljs.inherit(hljs.TITLE_MODE, Mode(begin = "^[_a-z][\\w\"]*")),
      COMMENT,

      Mode(begin = "->|<-") // No markup, relevance booster
    ),
    illegal = """;"""
  );
}
