package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Roboconf
Author = Vincent Zurczak <vzurczak@linagora.com>
Website = http = //roboconf.net
Description = Syntax highlighting for Roboconf's DSL
Category = config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/roboconf.js MD5 <f0e17f5918400873282f147efc907bcd>
 */
internal fun roboconf(): Mode {
    var IDENTIFIER = "[a-zA-Z-_][^\\n\\{]+\\{"
    var PROPERTY = Mode(
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
    return Mode(
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
                    hljs.SELF,
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
