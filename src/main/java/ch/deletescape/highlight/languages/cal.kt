package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = C/AL
Author = Kenneth Fuglsang Christensen <kfuglsang@gmail.com>
Description = Provides highlighting of Microsoft Dynamics NAV C/AL code files
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/cal.js MD5 <45e2a8d621427a3a744e763dd17a5769>
 */
internal fun cal(): Mode {
    var KEYWORDS =
        "div mod in and or not xor asserterror begin case do downto else end exit for if of repeat then to until while with var"
    var LITERALS = "false true"
    var COMMENT_MODES = listOf(
        hljs.C_LINE_COMMENT_MODE,
        hljs.COMMENT(
            """\{""", """\}""",
            Mode(
                relevance = 0
            )
        ),
        hljs.COMMENT(
            """\(\*""", """\*\)""",
            Mode(
                relevance = 10
            )
        )
    )
    var STRING = Mode(
        className = "string",
        begin =
            """'""",
        end =
            """'""",
        contains = listOf(Mode(begin =
                """''"""))
    )
    var CHAR_STRING = Mode(
        className = "string",
        begin =
            """(#\d+)+"""
    )
    var DATE = Mode(
        className = "number",
        begin = "\\b\\d+(\\.\\d+)?(DT|D|T)",
        relevance = 0
    )
    var DBL_QUOTED_VARIABLE = Mode(
        className = "string",
        // not a string technically but makes sense to be highlighted in the same style
        begin = "\"",
        end = "\""
    )
    var PROCEDURE = Mode(
        className = "function",
        beginKeywords = keywords("procedure"),
        end =
            """[:;]""",
        keywords = keywords("procedure|10"),
        contains = listOf(
            hljs.TITLE_MODE,
            Mode(
                className = "params",
                begin =
                    """\(""",
                end =
                    """\)""",
                keywords = keywords(KEYWORDS),
                contains = listOf(STRING, CHAR_STRING)
            )
        ) + COMMENT_MODES
    )
    var OBJECT = Mode(
        className = "class",
        begin = "OBJECT (Table|Form|Report|Dataport|Codeunit|XMLport|MenuSuite|Page|Query) (\\d+) ([^\\r\\n]+)",
        returnBegin = true,
        contains = listOf(
            hljs.TITLE_MODE,
            PROCEDURE
        )
    )
    return Mode(
        case_insensitive = true,
        keywords = listOf(
            keyword(
                className = "keyword",
                value = KEYWORDS
            ),
            keyword(
                className = "literal",
                value = LITERALS
            )
        ).flatten(),
        illegal =
            """\/\*""",
        contains = listOf(
            STRING, CHAR_STRING,
            DATE, DBL_QUOTED_VARIABLE,
            hljs.NUMBER_MODE,
            OBJECT,
            PROCEDURE
        )
    )
}
