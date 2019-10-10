package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
 Language = Gherkin
 Author = Sam Pikesley (@pikesley) <sam.pikesley@theodi.org>
 Description = Gherkin (Cucumber etc)
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/gherkin.js MD5 <189c161bf483dd0a4ba898d2f8204cf8>
 */
internal fun gherkin(): Mode {
    return Mode(
        aliases = listOf("feature"),
        keywords = keywords("Feature Background Ability Business\\ Need Scenario Scenarios Scenario\\ Outline Scenario\\ Template Examples Given And Then But When"),
        contains = listOf(
            Mode(
                className = "symbol",
                begin = "\\*",
                relevance = 0
            ),
            Mode(
                className = "meta",
                begin = "@[^@\\s]+"
            ),
            Mode(
                begin = "\\|",
                end = "\\|\\w*\$",
                contains = listOf(
                    Mode(
                        className = "string",
                        begin = "[^|]+"
                    )
                )
            ),
            Mode(
                className = "variable",
                begin = "<",
                end = ">"
            ),
            hljs.HASH_COMMENT_MODE,
            Mode(
                className = "string",
                begin = "\"\"\"",
                end = "\"\"\""
            ),
            hljs.QUOTE_STRING_MODE
        )
    )
}
