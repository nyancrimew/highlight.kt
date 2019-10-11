package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keywords

/*
Language = C#
Author = Jason Diamond <jason@diamond.name>
Contributor = Nicolas LLOBERA <nllobera@gmail.com>, Pieter Vantorre <pietervantorre@gmail.com>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/cs.js MD5 <655a3949a8d3e20b23d828760db9fea2>
 */
internal fun cs(): Mode {
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                // Normal keywords.
                "abstract as base bool break byte case catch char checked const continue decimal default delegate do double enum event explicit extern finally fixed float for foreach goto if implicit in int interface internal is lock long object operator out override params private protected public readonly ref sbyte sealed short sizeof stackalloc static string struct switch this try typeof uint ulong unchecked unsafe ushort using virtual void volatile while add alias ascending async await by descending dynamic equals from get global group into join let nameof on orderby partial remove select set value var when where yield"
        ),
        Keyword(
            className = "literal",
            value =
                "null false true"
        )
    )
    var NUMBERS = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0b[01\"]+)"),
            Mode(begin = "(-?)\\b([\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(u|U|l|L|ul|UL|f|F|b|B)"),
            Mode(begin = "(-?)(\\b0[xX][a-fA-F0-9\"]+|(\\b[\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)([eE][-+]?[\\d\"]+)?)")
        ),
        relevance = 0
    )
    var VERBATIM_STRING = Mode(
        className = "string",
        begin = "@\"",
        end = "\"",
        contains = listOf(Mode(begin = "\"\""))
    )
    var VERBATIM_STRING_NO_LF = hljs.inherit(
        VERBATIM_STRING,
        Mode(
            illegal =
                """\n"""
        )
    )
    var SUBST = Mode(
        className = "subst",
        begin = "\\{",
        end = "}",
        keywords = keywords(KEYWORDS)
    )
    var SUBST_NO_LF = hljs.inherit(
        SUBST,
        Mode(
            illegal =
                """\n"""
        )
    )
    var INTERPOLATED_STRING = Mode(
        className = "string",
        begin =
            """${'$'}"""",
        end = "\"",
        illegal =
            """\n""",
        contains = listOf(Mode(begin = "\\{\\{"), Mode(begin = "}}"), hljs.BACKSLASH_ESCAPE, SUBST_NO_LF)
    )
    var INTERPOLATED_VERBATIM_STRING = Mode(
        className = "string",
        begin =
            """${'$'}@"""",
        end = "\"",
        contains = listOf(Mode(begin = "\\{\\{"), Mode(begin = "}}"), Mode(begin = "\"\""), SUBST)
    )
    var INTERPOLATED_VERBATIM_STRING_NO_LF = hljs.inherit(
        INTERPOLATED_VERBATIM_STRING,
        Mode(
            illegal =
                """\n""",
            contains = listOf(Mode(begin = "\\{\\{"), Mode(begin = "}}"), Mode(begin = "\"\""), SUBST_NO_LF)
        )
    )
    SUBST.contains = listOf(
        INTERPOLATED_VERBATIM_STRING,
        INTERPOLATED_STRING,
        VERBATIM_STRING,
        hljs.APOS_STRING_MODE,
        hljs.QUOTE_STRING_MODE,
        NUMBERS,
        hljs.C_BLOCK_COMMENT_MODE
    )
    SUBST_NO_LF.contains = listOf(
        INTERPOLATED_VERBATIM_STRING_NO_LF,
        INTERPOLATED_STRING,
        VERBATIM_STRING_NO_LF,
        hljs.APOS_STRING_MODE,
        hljs.QUOTE_STRING_MODE,
        NUMBERS,
        hljs.inherit(
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                illegal =
                    """\n"""
            )
        )
    )
    var STRING = Mode(
        variants = listOf(
            INTERPOLATED_VERBATIM_STRING,
            INTERPOLATED_STRING,
            VERBATIM_STRING,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE
        )
    )
    var TYPE_IDENT_RE = hljs.IDENT_RE + "(<" +
        hljs.IDENT_RE + "(\\s*,\\s*" +
        hljs.IDENT_RE + ")*>)?(\\[\\])?"
    return Mode(
        aliases = listOf(
            "csharp",
            "c#"
        ),
        keywords = keywords(KEYWORDS),
        illegal =
            """::""",
        contains = listOf(
            hljs.COMMENT(
                "///",
                "${'\$'}",
                Mode(
                    returnBegin = true,
                    contains = listOf(
                        Mode(
                            className = "doctag",
                            variants = listOf(
                                Mode(begin = "///", relevance = 0),
                                Mode(
                                    begin = "<!--|-->"
                                ),
                                Mode(
                                    begin = "</?",
                                    end = ">"
                                )
                            )
                        )
                    )
                )
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "meta",
                begin = "#",
                end = "${'\$'}",
                keywords = listOf(
                    Keyword(
                        className = "meta-keyword",
                        value = "if else elif endif define undef warning error line region endregion pragma checksum"
                    )
                )
            ),
            STRING,
            NUMBERS,
            Mode(
                beginKeywords = keywords("class interface"),
                end =
                    """[{;=]""",
                illegal =
                    """[^\s = ,]""",
                contains = listOf(
                    hljs.TITLE_MODE,
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE
                )
            ),
            Mode(
                beginKeywords = keywords("namespace"),
                end =
                    """[\{;=]""",
                illegal =
                    """[^\s = ]""",
                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = "[a-zA-Z](\\.?\\w)*")),
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE
                )
            ),
            Mode(
                // listOf(Attributes(""))
                className = "meta",
                begin = "^\\s*\\[",
                excludeBegin = true,
                end = "\\]",
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        className = "meta-string",
                        begin =
                            """"""",
                        end =
                            """""""
                    )
                )
            ),
            Mode(
                // Expression keywords prevent "keyword Name(...)" from being
                // recognized as a function definition
                beginKeywords = keywords("new return throw await else"),
                relevance = 0
            ),
            Mode(
                className = "function",
                begin = "(" +
                    TYPE_IDENT_RE + "\\s+)+" +
                    hljs.IDENT_RE + "\\s*\\(",
                returnBegin = true,
                end =
                    """\s*[\{;=]""",
                excludeEnd = true,
                keywords = keywords(KEYWORDS),
                contains = listOf(
                    Mode(
                        begin = hljs.IDENT_RE + "\\s*\\(",
                        returnBegin = true,
                        contains = listOf(hljs.TITLE_MODE),
                        relevance = 0
                    ),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        excludeBegin = true,
                        excludeEnd = true,
                        keywords = keywords(KEYWORDS),
                        relevance = 0,
                        contains = listOf(
                            STRING,
                            NUMBERS,
                            hljs.C_BLOCK_COMMENT_MODE
                        )
                    ),
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE
                )
            )
        )
    )
}
