package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Scala
Category = functional
Author = Jan Berkel <jan.berkel@gmail.com>
Contributors = Erik Osheim <d_m@plastic-idolatry.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ScalaLanguage : LanguageBuilder  {

  val ANNOTATION = Mode( className = "meta",

 begin = "@[A-Za-z]+" );

  // used in strings for escaping/interpolation/substitution
  val SUBST = Mode(
    className = "subst",

    variants = listOf(
      Mode(begin = "\\$[A-Za-z0-9_]+"),
      Mode(begin = "\\${",

 end = "}")
    )
  );

  val STRING = Mode(
    className = "string",

    variants = listOf(
      Mode(
        begin = "\"",

 end = "\"",

        illegal = "\\n",

        contains = listOf(hljs.BACKSLASH_ESCAPE)
      ),
      Mode(
        begin = "\"\"\"",

 end = "\"\"\"",

        relevance = 10
      ),
      Mode(
        begin = "[a-z]+\"",

 end = "\"",

        illegal = "\\n",

        contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
      ),
      Mode(
        className = "string",

        begin = "[a-z]+\"\"\"",

 end = "\"\"\"",

        contains = listOf(SUBST),
        relevance = 10
      )
    )

  );

  val SYMBOL = Mode(
    className = "symbol",

    begin = "\'\\w[\\w\\d_]*(?!\")"
  );

  val TYPE = Mode(
    className = "type",

    begin = "\\b[A-Z][A-Za-z0-9_]*",

    relevance = 0
  );

  val NAME = Mode(
    className = "title",

    begin = """[^0-9\n\t "'(),.`{}\[\]:;][^\n\t \"'(),.`{}\[\]:;]+|[^0-9\n\t \"'(),.`{}\[\]:;=]\"\"",

    relevance = 0
  );

  val CLASS = Mode(
    className = "class",

    beginKeywords = keywords("class object trait type"),
    end = """[:=Mode(\[\n;]""",

    excludeEnd = true,
    contains = listOf(
      Mode(
        beginKeywords = keywords("extends with"),
        relevance = 10
      ),
      Mode(
        begin = """\[""",

        end = """\]""",

        excludeBegin = true,
        excludeEnd = true,
        relevance = 0,
        contains = listOf(TYPE)
      ),
      Mode(
        className = "params",

        begin = """\(""",

        end = """\)""",

        excludeBegin = true,
        excludeEnd = true,
        relevance = 0,
        contains = listOf(TYPE)
      ),
      NAME
    )
  );

  val METHOD = Mode(
    className = "function",

    beginKeywords = keywords("def"),
    end = """[:=Mode(\[(\n;]""",

    excludeEnd = true,
    contains = listOf(NAME)
  );

  override fun build() = Mode(
    keywords = listOf(
      Keyword(className = "literal",

 value = "true false null"),
      Keyword(className = "keyword",

 value = "type yield lazy override def with val val sealed abstract private trait object if forSome for while throw finally protected extends import final return else break new catch super class case package default try this match continue throws implicit"
    )),
    contains = listOf(
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      STRING,
      SYMBOL,
      TYPE,
      METHOD,
      CLASS,
      hljs.C_NUMBER_MODE,
      ANNOTATION
    )
  );
}
