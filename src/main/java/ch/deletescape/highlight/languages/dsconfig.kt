package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
 Language = dsconfig
 Description = dsconfig batch configuration language for LDAP directory servers
 Contributors = Jacob Childress <jacobc@gmail.com>
 Category = enterprise, config
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/dsconfig.js MD5 <546e7af8847357393d400d4d48098112>
 */
internal fun dsconfig(): Mode {
    var QUOTED_PROPERTY = Mode(
        className = "string",
        begin =
            """"""",
        end =
            """""""
    )
    var APOS_PROPERTY = Mode(
        className = "string",
        begin =
            """'""",
        end =
            """'"""
    )
    var UNQUOTED_PROPERTY = Mode(
        className = "string",
        begin = "[\\w-?]+:\\w+",
        end = "\\W",
        relevance = 0
    )
    var VALUELESS_PROPERTY = Mode(
        className = "string",
        begin = "\\w+-?\\w+",
        end = "\\W",
        relevance = 0
    )
    return Mode(
        keywords = keywords("dsconfig"),
        contains = listOf(
            Mode(
                className = "keyword",
                begin = "^dsconfig",
                end = "\\s",
                excludeEnd = true,
                relevance = 10
            ),
            Mode(
                className = "built_in",
                begin = "(list|create|get|set|delete)-(\\w+)",
                end = "\\s",
                excludeEnd = true,
                illegal = "!@#\$%^&*()",
                relevance = 10
            ),
            Mode(
                className = "built_in",
                begin = "--(\\w+)",
                end = "\\s",
                excludeEnd = true
            ),
            QUOTED_PROPERTY,
            APOS_PROPERTY,
            UNQUOTED_PROPERTY,
            VALUELESS_PROPERTY,
            hljs.HASH_COMMENT_MODE
        )
    )
}
