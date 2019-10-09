package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
 Language = Flix
 Category = functional
 Author = Magnus Madsen <mmadsen@uwaterloo.ca>
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/flix.js MD5 <4dc3e1f4f7d4b76289d993baa39c217d>
 */
internal fun flix(): Mode {
    var CHAR = Mode(
        className = "string",
        begin =
            """'(.|\\[xXuU][a-zA-Z0-9]+)'"""
    )
    var STRING = Mode(
        className = "string",
        variants = listOf(
            Mode(
                begin = "\"",
                end = "\""
            )
        )
    )
    var NAME = Mode(
        className = "title",
        begin =
            """[^0-9\n\t "'(),.`{}\[\]:;][^\n\t \"'(),.`{}\[\]:;]+|[^0-9\n\t \"'(),.`{}\[\]:;=]\"""
    )
    var METHOD = Mode(
        className = "function",
        beginKeywords = keywords("def"),
        end =
            """[:=Mode(\[(\n;]""",
        excludeEnd = true,
        contains = listOf(NAME)
    )
    return Mode(
        keywords = listOf(
            Keyword(
                className = "literal",
                value = "true false"
            ),
            Keyword(
                className = "keyword",
                value = "case class def else enum if impl import in lat rel index let match namespace switch type yield with"
            )
        ),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            CHAR,
            STRING,
            METHOD,
            hljs.C_NUMBER_MODE
        )
    )
}
