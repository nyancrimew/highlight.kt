package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keywords

/*
Language = Twig
Requires = xml.js
Author = Luke Holder <lukemh@gmail.com>
Description = Twig is a templating language for PHP
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/twig.js MD5 <f74a14f620eba5879ada1a996262172d>
 */
internal fun twig(): Mode {
    var PARAMS = Mode(
        className = "params",
        begin = "\\(",
        end = "\\)"
    )
    var FUNCTION_NAMES =
        "attribute block constant cycle date dump include max min parent random range source template_from_string"
    var FUNCTIONS = Mode(
        beginKeywords = keywords(FUNCTION_NAMES),
        keywords = listOf(
            Keyword(
                className = "name",
                value = FUNCTION_NAMES
            )
        ),
        relevance = 0,
        contains = listOf(
            PARAMS
        )
    )
    var FILTER = Mode(
        begin =
            """\|[A-Za-z_]+:?""",
        keywords =
            keywords("abs batch capitalize convert_encoding date date_modify default escape first format join json_encode keys last length lower merge nl2br number_format raw replace reverse round slice sort split striptags title trim upper url_encode"),
        contains = listOf(
            FUNCTIONS
        )
    )
    var TAGS =
        "autoescape block do embed extends filter flush for if import include macro sandbox set spaceless use verbatim"
    TAGS = TAGS + " " +
        TAGS.split(" ").map { t ->
            "end" +
                t
        }.joinToString(" ")
    return Mode(
        aliases = listOf("craftcms"),
        case_insensitive = true,
        subLanguage = "xml",
        contains = listOf(
            hljs.COMMENT("""\{#""", """#}"""),
            Mode(
                className = "template-tag",
                begin =
                    """\{%""",
                end =
                    """%}""",
                contains = listOf(
                    Mode(
                        className = "name",
                        begin =
                            """\w+""",
                        keywords = keywords(TAGS),
                        starts = Mode(
                            endsWithParent = true,
                            contains = listOf(FILTER, FUNCTIONS),
                            relevance = 0
                        )
                    )
                )
            ),
            Mode(
                className = "template-variable",
                begin =
                    """\{\{""",
                end =
                    """}}""",
                contains = listOf(
                    hljs.SELF,
                    FILTER, FUNCTIONS
                )
            )
        )
    )
}
