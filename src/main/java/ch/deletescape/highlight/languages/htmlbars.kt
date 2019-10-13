package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keyword

/*
Language = HTMLBars
Requires = xml.js
Author = Michael Johnston <lastobelus@gmail.com>
Description = Matcher for HTMLBars
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/htmlbars.js MD5 <59fc7dd85a788090bc0a7dc2889ee5f0>
 */
internal fun htmlbars(): Mode {
    var BUILT_INS =
        "action collection component concat debugger each each-in else get hash if input link-to loc log mut outlet partial query-params render textarea unbound unless with yield view"
    var ATTR_ASSIGNMENT = Mode(
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
    var SUB_EXPR = Mode(
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
                keywords =
                keyword(
                    className = "built_in",
                    value = BUILT_INS
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
    var TAG_INNARDS = Mode(
        endsWithParent = true,
        relevance = 0,
        keywords = listOf(
            keyword(
                className = "keyword",
                value = "as"
            ),
            keyword(
                className = "built_in",
                value = BUILT_INS
            )
        ).flatten(),
        contains = listOf(
            hljs.QUOTE_STRING_MODE,
            ATTR_ASSIGNMENT,
            hljs.NUMBER_MODE
        )
    )
    return Mode(
        case_insensitive = true,
        subLanguage = "xml",
        contains = listOf(
            hljs.COMMENT(
                "\\{\\{!(--)?",
                "(--)?\\}\\}"
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
                        keywords =                             keyword(
                                className = "builtin-name",
                                value = BUILT_INS

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
                keywords = listOf(
                    keyword(
                        className = "keyword",
                        value = "as"
                    ),
                    keyword(
                        className = "built_in",
                        value = BUILT_INS
                    )
                ).flatten(),
                contains = listOf(
                    hljs.QUOTE_STRING_MODE
                )
            )
        )
    )
}
