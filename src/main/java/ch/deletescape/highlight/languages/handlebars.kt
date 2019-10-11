package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Handlebars
Requires = xml.js
Author = Robin Ward <robin.ward@gmail.com>
Description = Matcher for Handlebars as well as EmberJS additions.
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/handlebars.js MD5 <129fe49dd4318e5b61064e5c2a39cd1c>
 */
internal fun handlebars(): Mode {
    var BUILT_INS = listOf(
        Keyword(
            className = "builtin-name",
            value = "each in with if else unless bindattr action collection debugger log outlet template unbound view yield"
        )
    )
    return Mode(
        aliases = listOf(
            "hbs",
            "html.hbs",
            "html.handlebars"
        ),
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
                            """[a-zA-Z\.-]+""",
                        keywords = keywords(BUILT_INS),
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
                    """\{\{""",
                end =
                    """\}\}""",
                keywords = keywords(BUILT_INS)
            )
        )
    )
}
