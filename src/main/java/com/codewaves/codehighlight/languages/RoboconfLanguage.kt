package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Roboconf
Author = Vincent Zurczak <vzurczak@linagora.com>
Website = http://roboconf.net
Description = Syntax highlighting for Roboconf's DSL
Category = config
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class RoboconfLanguage : LanguageBuilder {
    val IDENTIFIER = "[a-zA-Z-_][^\\n{]+\\{"

    val PROPERTY = Mode(
        className = "attribute",

        begin =
            """[a-zA-Z-_]+""",

        end =
            """\s*:""",

        excludeEnd = true,
        starts = Mode(
            end = ";",

            relevance = 0,
            contains = listOf(
                Mode(
                    className = "variable",

                    begin =
                        """\.[a-zA-Z-_]+"""
                ),
                Mode(
                    className = "keyword",

                    begin =
                        """\(optional\)"""
                )
            )
        )
    )

    override fun build() = Mode(
        aliases = listOf(
            "graph",
            "instances"
        ),
        case_insensitive = true,
        keywords = keywords("import"),
        contains = listOf(
            // Facet sections
            Mode(
                begin = "^facet " +
                    IDENTIFIER,
                end = "}",

                keywords = keywords("facet"),
                contains = listOf(
                    PROPERTY,
                    hljs.HASH_COMMENT_MODE
                )
            ),

            // Instance sections
            Mode(
                begin = "^\\s*instance of " +
                    IDENTIFIER,
                end = "}",

                keywords = keywords("name count channels instance-data instance-state instance of"),
                illegal =
                    """\S""",

                contains = listOf(

                    PROPERTY,
                    hljs.HASH_COMMENT_MODE
                )
            ),

            // Component sections
            Mode(
                begin = "^" +
                    IDENTIFIER,
                end = "}",

                contains = listOf(
                    PROPERTY,
                    hljs.HASH_COMMENT_MODE
                )
            ),

            // Comments
            hljs.HASH_COMMENT_MODE
        )
    )
}
