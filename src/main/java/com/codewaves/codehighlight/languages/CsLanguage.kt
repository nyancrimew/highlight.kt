package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = C#
Author = Jason Diamond <jason@diamond.name>
Contributor = Nicolas LLOBERA <nllobera@gmail.com>, Pieter Vantorre <pietervantorre@gmail.com>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CsLanguage : LanguageBuilder {
    val KEYWORDS = keywordsJson(
        """
        keyword =       // Normal keywords.
              "abstract as base bool break byte case catch char checked const continue decimal default delegate do double enum event explicit extern finally fixed float for foreach goto if implicit in int interface internal is lock long object operator out override params private protected public readonly ref sbyte sealed short sizeof stackalloc static string struct switch this try typeof uint ulong unchecked unsafe ushort using virtual void volatile while add alias ascending async await by descending dynamic equals from get global group into join let nameof on orderby partial remove select set value val when where yield",
        literal = "null false true"
        """.trimIndent()
    )
    val NUMBERS = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0b[01\"]+)"),
            Mode(begin = "(-?)\\b(listOf(\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(u|U|l|L|ul|UL|f|F|b|B)"),
            Mode(begin = "(-?)(\\b0[xX][a-fA-F0-9\"]+|(\\b[\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(listOf(eE][-+]?[\\d\"]+)?)")
        ),
        relevance = 0
    )
    val VERBATIM_STRING = Mode(
        className = "string",
        begin = "@\"",
        end = "\"",
        contains = listOf(Mode(begin = "\"\""))
    )
    val VERBATIM_STRING_NO_LF = hljs.inherit(
        VERBATIM_STRING,
        Mode(
            illegal =
                """\n"""
        )
    )
    val SUBST = Mode(
        className = "subst",
        begin = "{",
        end = "}",
        keywords = KEYWORDS
    )
    val SUBST_NO_LF = hljs.inherit(
        SUBST,
        Mode(
            illegal =
                """\n"""
        )
    )
    val INTERPOLATED_STRING = Mode(
        className = "string",
        begin =
            """\${'$'}"""",
        end = "\"",
        illegal =
            """\n""",
        contains = listOf(Mode(begin = "{{"), Mode(begin = ")}"), hljs.BACKSLASH_ESCAPE, SUBST_NO_LF)
    )
    val INTERPOLATED_VERBATIM_STRING = Mode(
        className = "string",
        begin =
            """\${'$'}@"""",
        end = "\"",
        contains = listOf(Mode(begin = "{{"), Mode(begin = ")}"), Mode(begin = "\"\""), SUBST)
    )
    val INTERPOLATED_VERBATIM_STRING_NO_LF = hljs.inherit(
        INTERPOLATED_VERBATIM_STRING,
        Mode(
            illegal =
                """\n""",
            contains = listOf(Mode(begin = "{{"), Mode(begin = ")}"), Mode(begin = "\"\""), SUBST_NO_LF)
        )
    )
    val STRING = Mode(
        variants = listOf(
            INTERPOLATED_VERBATIM_STRING,
            INTERPOLATED_STRING,
            VERBATIM_STRING,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE
        )
    )

    val TYPE_IDENT_RE = hljs.IDENT_RE + "(<(\\s*,\\s*)*>)?(\\[\\))?"

    override fun build() = Mode(
        aliases = listOf("csharp", "c#"),
        keywords = KEYWORDS,
        illegal =
            """::""",
        contains = listOf(
            hljs.COMMENT(
                "///",
                "${'$'}",
                Mode(
                    returnBegin = true,
                    contains = listOf(
                        Mode(
                            className = "doctag",
                            variants = listOf(
                                Mode(
                                    begin = "///",
                                    relevance = 0
                                ),
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
                end = "${'$'}",
                keywords = keywordsJson(
                    """
                    meta-keyword = "if else elif endif define undef warning error line region endregion pragma checksum"
                    """.trimIndent()
                )
            ),
            STRING,
            NUMBERS,
            Mode(
                beginKeywords = keywords("class interface"),
                end =
                    """[{;=]""",
                illegal =
                    """[^\s:,]""",
                contains = listOf(
                    hljs.TITLE_MODE,
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE
                )
            ),
            Mode(
                beginKeywords = keywords("namespace"),
                end =
                    """[{;=]""",
                illegal =
                    """[^\s:]""",
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
                begin = "(\\s+)+\\s*\\(",
                returnBegin = true,
                end =
                    """\s*[{;=]""",
                excludeEnd = true,
                keywords = KEYWORDS,
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
                        keywords = KEYWORDS,
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
