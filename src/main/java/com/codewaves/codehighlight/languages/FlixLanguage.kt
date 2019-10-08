package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = Flix
 Category = functional
 Author = Magnus Madsen <mmadsen@uwaterloo.ca>
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class FlixLanguage : LanguageBuilder  {

    val CHAR = Mode(
        className = "string",

        begin = """'(.|\\[xXuU][a-zA-Z0-9]+)'"""
    );

    val STRING = Mode(
        className = "string",

        variants = listOf(
            Mode(
                begin = "\"",

 end = "\""
            )
        )
    );

    val NAME = Mode(
        className = "title",

        begin = """[^0-9\n\t "'(),.`{}\[\]:;][^\n\t \"'(),.`{}\[\]:;]+|[^0-9\n\t \"'(),.`{}\[\]:;=]\"\""
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

 value = "true false"),
            Keyword(className = "keyword",

 value = "case class def else enum if impl import in lat rel index let match namespace switch type yield with"
        )),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            CHAR,
            STRING,
            METHOD,
            hljs.C_NUMBER_MODE
        )
    );
}
