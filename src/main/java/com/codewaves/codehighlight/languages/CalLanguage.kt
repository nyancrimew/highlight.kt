package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = C/AL
Author = Kenneth Fuglsang Christensen <kfuglsang@gmail.com>
Description = Provides highlighting of Microsoft Dynamics NAV C/AL code files
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CalLanguage : LanguageBuilder  {
  val KEYWORDS = "div mod in and or not xor asserterror begin case do downto else end exit for if of repeat then to until while with var";
  val LITERALS = "false true";
  val COMMENT_MODES = listOf(
    hljs.C_LINE_COMMENT_MODE,
    hljs.COMMENT("""\{""","""\}""",

      Mode(
        relevance = 0
      )
    ),
    hljs.COMMENT("""\(\*""","""\*\)""",

      Mode(
        relevance = 10
      )
    )
  );
  val STRING = Mode(
    className = "string",

    begin = """'""",

 end = """'""",

    contains = listOf(Mode(begin = """''"""))
  );
  val CHAR_STRING = Mode(
    className = "string",

 begin = """(#\d+)+"""
  );
  val DATE = Mode(
      className = "number",

      begin = "\\b\\d+(\\.\\d+)?(DT|D|T)",

      relevance = 0
  );
  val DBL_QUOTED_VARIABLE = Mode(
      className = "string",
 // not a string technically but makes sense to be highlighted in the same style
      begin = "\"",

      end = "\""
  );

  val PROCEDURE = Mode(
    className = "function",

    beginKeywords = keywords("procedure",

 end = """[:;]"""),
    keywords = keywords("procedure|10"),
    contains = listOf(
      hljs.TITLE_MODE,
      Mode(
        className = "params",

        begin = """\(""",

 end = """\)""",

        keywords = keywords(KEYWORDS),
        contains = listOf(STRING, CHAR_STRING)
      )
    ) + COMMENT_MODES)
  );

  val OBJECT = Mode(
    className = "class",

    begin = "OBJECT (Table|Form|Report|Dataport|Codeunit|XMLport|MenuSuite|Page|Query) (\\d+) (listOf(^\\r\\n]+)",

    returnBegin = true,
    contains = listOf(
      hljs.TITLE_MODE,
        PROCEDURE
    )
  );

  override fun build() = Mode(
    case_insensitive = true,
    keywords = keywords(listOf( Keyword(className = "keyword",

 value = KEYWORDS), Keyword(className = "literal",

 value = LITERALS ))),
    illegal = """\/\*""",

    contains = listOf(
      STRING, CHAR_STRING,
      DATE, DBL_QUOTED_VARIABLE,
      hljs.NUMBER_MODE,
      OBJECT,
      PROCEDURE
    )
  );
}
