package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Dust
Requires = xml.js
Author = Michael Allen <michael.allen@benefitfocus.com>
Description = Matcher for dust.js templates.
Category = template
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DustLanguage : LanguageBuilder {
    val EXPRESSION_KEYWORDS = "if eq ne lt lte gt gte select default math sep"
    override fun build() = Mode(
        aliases = listOf("dst"),
        case_insensitive = true,
        subLanguage = "xml",

        contains = listOf(
            Mode(
                className = "template-tag",

                begin =
                    """\{[#\/]""",

                end =
                    """\}""",

                illegal =
                    """;""",

                contains = listOf(
                    Mode(
                        className = "name",

                        begin =
                            """[a-zA-Z\.-]+""",

                        starts = Mode(
                            endsWithParent = true,
                            relevance = 0,
                            contains = listOf(
                                hljs.QUOTE_STRING_MODE
                            )
                        )
                    )
                )
            ),
            Mode(
                className = "template-variable",

                begin =
                    """\{""",

                end =
                    """\}""",

                illegal =
                    """;""",

                keywords = EXPRESSION_KEYWORDS
            )
        )
    )
}
