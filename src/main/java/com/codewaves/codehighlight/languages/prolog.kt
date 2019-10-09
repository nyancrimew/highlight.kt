package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Prolog
Description = Prolog is a general purpose logic programming language associated with artificial intelligence and computational linguistics.
Author = Raivo Laanemets <raivo@infdot.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/prolog.js MD5 <92196b523d6f3d22d99aed4a25fa8637>
 */
internal fun prolog(): Mode {
    var ATOM = Mode(
        begin =
            """[a-z][A-Za-z0-9_]*""",
        relevance = 0
    )
    var VAR = Mode(
        className = "symbol",
        variants = listOf(
            Mode(begin =
                    """[A-Z][a-zA-Z0-9_]*"""),
            Mode(begin =
                    """_[A-Za-z0-9_]*""")
        ),
        relevance = 0
    )
    var PARENTED = Mode(
        begin =
            """\(""",
        end =
            """\)""",
        relevance = 0
    )
    var LIST = Mode(
        begin =
            """\[""",
        end =
            """\]"""
    )
    var LINE_COMMENT = Mode(
        className = "comment",
        begin =
            """%""",
        end =
            """${'$'}""",
        contains = listOf(hljs.PHRASAL_WORDS_MODE)
    )
    var BACKTICK_STRING = Mode(
        className = "string",
        begin =
            """`""",
        end =
            """`""",
        contains = listOf(hljs.BACKSLASH_ESCAPE)
    )
    var CHAR_CODE = Mode(
        className = "string", // 0'a etc.
        begin =
            """0'(\\'|.)"""
    )
    var SPACE_CODE = Mode(
        className = "string",
        begin =
            """0'\\s""" // 0'\s
    )
    var PRED_OP = Mode(// relevance booster
        begin =
            """:-"""
    )
    var inner = listOf(
        ATOM,
        VAR,
        PARENTED,
        PRED_OP,
        LIST,
        LINE_COMMENT,
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.QUOTE_STRING_MODE,
        hljs.APOS_STRING_MODE,
        BACKTICK_STRING,
        CHAR_CODE,
        SPACE_CODE,
        hljs.C_NUMBER_MODE
    )
    PARENTED.contains = inner
    LIST.contains = inner
    return Mode(
        contains = inner + listOf(
            Mode(
                begin =
                    """\.${'$'}""" // relevance booster
            )
        )
    )
}
