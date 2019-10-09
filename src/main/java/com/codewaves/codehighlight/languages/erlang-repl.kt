package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
 Language = Erlang REPL
 Author = Sergey Ignatov <sergey@ignatov.spb.su>
Category = functional
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/erlang-repl.js MD5 <53d0da5b11c06d86aa44f819c4cd9392>
 */
internal fun `erlang-repl`(): Mode {
    return Mode(
        keywords = listOf(
            Keyword(
                className = "built_in",
                value =
                    "spawn spawn_link self"
            ),
            Keyword(
                className = "keyword",
                value =
                    "after and andalso|10 band begin bnot bor bsl bsr bxor case catch cond div end fun if let not of or orelse|10 query receive rem try when xor"
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
                begin = "\\b(\\d+#[a-fA-F0-9]+|\\d+(\\.\\d+)?([eE][-+]?\\d+)?)",
                relevance = 0
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                begin = "\\?(::)?([A-Z]\\w*(::)?)+"
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
