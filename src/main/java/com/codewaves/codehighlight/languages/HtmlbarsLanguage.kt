package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = HTMLBars
Requires = xml.js
Author = Michael Johnston <lastobelus@gmail.com>
Description = Matcher for HTMLBars
Category = template
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class HtmlbarsLanguage : LanguageBuilder {
    val BUILT_INS = "action collection component concat debugger each each-in else get hash if input link-to loc log mut outlet partial query-params render textarea unbound unless with yield view"

    val ATTR_ASSIGNMENT = Mode(
        illegal =
            """\}\}""",

        begin =
            """[a-zA-Z0-9_]+=""",

        returnBegin = true,
        relevance = 0,
        contains = listOf(
            Mode(
                className = "attr",

                begin =
                    """[a-zA-Z0-9_]+"""
            )
        )
    )

    val SUB_EXPR = Mode(
        illegal =
            """\}\}""",

        begin =
            """\)""",

        end =
            """\)""",

        contains = listOf(
            Mode(
                begin =
                    """[a-zA-Z\.\-]+""",

                keywords = keywords(
                    listOf(
                        Keyword(
                            className = "built_in",

                            value = BUILT_INS
                        )
                    )
                ),
                starts = Mode(
                    endsWithParent = true,
                    relevance = 0,
                    contains = listOf(
                        hljs.QUOTE_STRING_MODE
                    )
                )
            )
        )
    )

    val TAG_INNARDS = Mode(
        endsWithParent = true,
        relevance = 0,
        keywords = keywords(
            listOf(
                Keyword(
                    className = "keyword",

                    value = "as\"), Keyword(className = \"built_in",

                    value = BUILT_INS
                )
            )
        ),
        contains = listOf(
            hljs.QUOTE_STRING_MODE,
            ATTR_ASSIGNMENT,
            hljs.NUMBER_MODE
        )
    )

    override fun build() = Mode(
        case_insensitive = true,
        subLanguage = "xml",

        contains = listOf(
            hljs.COMMENT(
                "{{!(--)?",
                "(--)?)}"
            ),
            Mode(
                className = "template-tag",

                begin =
                    """\{\{[#\/]""",

                end =
                    """\}\}""",

                contains = listOf(
                    Mode(
                        className = "name",

                        begin =
                            """[a-zA-Z\.\-]+""",

                        keywords = keywords(
                            listOf(
                                Keyword(
                                    className = "builtin-name",

                                    value = BUILT_INS
                                )
                            )
                        ),
                        starts = TAG_INNARDS
                    )
                )
            ),
            Mode(
                className = "template-variable",

                begin =
                    """\{\{[a-zA-Z][a-zA-Z\-]+""",

                end =
                    """\}\}""",

                keywords = keywords(
                    listOf(
                        Keyword(
                            className = "keyword",

                            value = "as\"), Keyword(className = \"built_in",

                            value = BUILT_INS
                        )
                    )
                ),
                contains = listOf(
                    hljs.QUOTE_STRING_MODE
                )
            )
        )
    )
}
