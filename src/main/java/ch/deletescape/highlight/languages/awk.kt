package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keyword

/*
Language = Awk
Author = Matthew Daly <matthewbdaly@gmail.com>
Website = http = //matthewdaly.co.uk/
Description = language definition for Awk scripts
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/awk.js MD5 <f4f42a60dabdc60a081422ab475c3104>
 */
internal fun awk(): Mode {
    var VARIABLE = Mode(
        className = "variable",
        variants = listOf(
            Mode(
                begin =
                """\$[\w\d#@][\w\d_]*"""
            ),
            Mode(
                begin =
                """\$\{(.*?)}"""
            )
        )
    )
    var KEYWORDS = "BEGIN END if else while do for in break continue delete next nextfile function func exit|10"
    var STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE),
        variants = listOf(
            Mode(
                begin =
                """(u|b)?r?'''""",
                end =
                """'''""",
                relevance = 10
            ),
            Mode(
                begin =
                """(u|b)?r?"""""",
                end =
                """"""""",
                relevance = 10
            ),
            Mode(
                begin =
                """(u|r|ur)'""",
                end =
                """'""",
                relevance = 10
            ),
            Mode(
                begin =
                """(u|r|ur)"""",
                end =
                """"""",
                relevance = 10
            ),
            Mode(
                begin =
                """(b|br)'""",
                end =
                """'"""
            ),
            Mode(
                begin =
                """(b|br)"""",
                end =
                """""""
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE
        )
    )
    return Mode(
        keywords = keyword(
            className = "keyword",
            value = KEYWORDS
        ),
        contains = listOf(
            VARIABLE,
            STRING,
            hljs.REGEXP_MODE,
            hljs.HASH_COMMENT_MODE,
            hljs.NUMBER_MODE
        )
    )
}
