package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Dust
Requires = xml.js
Author = Michael Allen <michael.allen@benefitfocus.com>
Description = Matcher for dust.js templates.
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/dust.js MD5 <31c1d857403483b6ca78baa6b045eb97>
 */
internal fun dust(): Mode {
    var EXPRESSION_KEYWORDS = "if eq ne lt lte gt gte select default math sep"
    return Mode(
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
                keywords = keywords(EXPRESSION_KEYWORDS)
            )
        )
    )
}
