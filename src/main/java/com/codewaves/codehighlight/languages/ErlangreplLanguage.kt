package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = Erlang REPL
 Author = Sergey Ignatov <sergey@ignatov.spb.su>
Category = functional
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ErlangreplLanguage : LanguageBuilder {
    override fun build() = Mode(
        keywords = listOf(
            Keyword(
                className = "built_in",

                value = "spawn spawn_link self"
            ),
            Keyword(
                className = "keyword",

                value = "after and andalso|10 band begin bnot bor bsl bsr bxor case catch cond div end fun if let not of or orelse|10 query receive rem try when xor"
            )
        ),
        contains = listOf(
            Mode(
                className = "meta",

                begin = "^[0-9]+> ",

                relevance = 10
            ),
            hljs.COMMENT(
                "%",
                "\$"
            ),
            Mode(
                className = "number",

                begin = "\\b(\\d+#[a-fA-F0-9]+|\\d+(\\.\\d+)?(listOf(eE][-+]?\\d+)?)",

                relevance = 0
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                begin = "\\?(::)?(listOf(A-Z]\\w*(::)?)+"
            ),
            Mode(
                begin = "->"
            ),
            Mode(
                begin = "ok"
            ),
            Mode(
                begin = "!"
            ),
            Mode(
                begin = "(\\b[a-z\"][a-zA-Z0-9_\"]*:[a-z\"][a-zA-Z0-9_\"]*)|(\\b[a-z\"][a-zA-Z0-9_\"]*)",

                relevance = 0
            ),
            Mode(
                begin = "[A-Z][a-zA-Z0-9_\"]*",

                relevance = 0
            )
        )
    )
}
