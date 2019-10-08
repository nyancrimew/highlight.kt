package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = dsconfig
 Description = dsconfig batch configuration language for LDAP directory servers
 Contributors = Jacob Childress <jacobc@gmail.com>
 Category = enterprise, config
 */
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DsconfigLanguage : LanguageBuilder {
    val QUOTED_PROPERTY = Mode(
        className = "string",

        begin =
            """"""",

        end =
            """""""
    )
    val APOS_PROPERTY = Mode(
        className = "string",

        begin =
            """'""",

        end =
            """'"""
    )
    val UNQUOTED_PROPERTY = Mode(
        className = "string",

        begin = "[\\w-?]+:\\w+",

        end = "\\W",

        relevance = 0
    )
    val VALUELESS_PROPERTY = Mode(
        className = "string",

        begin = "\\w+-?\\w+",

        end = "\\W",

        relevance = 0
    )

    override fun build() = Mode(
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
