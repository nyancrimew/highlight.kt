package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = YAML
Author = Stefan Wienert <stwienert@gmail.com>
Requires = ruby.js
Description = YAML (Yet Another Markdown Language)
Category = common, config
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class YamlLanguage : LanguageBuilder {
    val LITERALS = "true false yes no null"

    val keyPrefix = "^[ \\-]*"
    val keyName = "[a-zA-Z_][\\w\\-]*"
    val KEY = Mode(
        className = "attr",

        variants = listOf(
            Mode(begin = keyPrefix + keyName + ":"),
            Mode(
                begin = keyPrefix + "\"" +
                    keyName + "\":"
            ),
            Mode(
                begin = keyPrefix + "'" +
                    keyName + "':"
            )
        )
    )

    val TEMPLATE_VARIABLES = Mode(
        className = "template-variable",

        variants = listOf(
            Mode(
                begin = "\{\{",

                end = "\}\}"
            ),
            // jinja templates Ansible
            Mode(
                begin = "%\{",

                end = "\}"
            ) // Ruby i18n
        )
    )
    val STRING = Mode(
        className = "string",

        relevance = 0,
        variants = listOf(
            Mode(
                begin =
                    """'""",

                end =
                    """'"""
            ),
            Mode(
                begin =
                    """"""",

                end =
                    """""""
            ),
            Mode(begin =
                    """\S+""")
        ),
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            TEMPLATE_VARIABLES
        )
    )

    override fun build() = Mode(
        case_insensitive = true,
        aliases = listOf(
            "yml",
            "YAML",
            "yaml"
        ),
        contains = listOf(
            KEY,
            Mode(
                className = "meta",

                begin = "^---\s*\$",

                relevance = 10
            ),
            Mode(// multi line string
                className = "string",

                begin = "[\\|>) *\$",

                returnEnd = true,
                contains = STRING.contains,
                // very simple termination = next hash key
                end = KEY.variants[0].begin
            ),
            Mode(// Ruby/Rails erb
                begin = "<%[%=-]?",

                end = "[%-]?%>",

                subLanguage = "ruby",

                excludeBegin = true,
                excludeEnd = true,
                relevance = 0
            ),
            Mode(// local tags
                className = "type",

                begin = "!" +
                    hljs.UNDERSCORE_IDENT_RE
            ),
            Mode(// data type
                className = "type",

                begin = "!!" +
                    hljs.UNDERSCORE_IDENT_RE
            ),
            Mode(// fragment id &ref
                className = "meta",

                begin = "&" +
                    hljs.UNDERSCORE_IDENT_RE + "\$"

            ),
            Mode(// fragment reference *ref
                className = "meta",

                begin = "\\*" +
                    hljs.UNDERSCORE_IDENT_RE + "\$"
            ),
            Mode(// array listing
                className = "bullet",

                begin = "^ *-",

                relevance = 0
            ),
            hljs.HASH_COMMENT_MODE,
            Mode(
                beginKeywords = keywords(LITERALS),
                keywords = keywords(
                    listOf(
                        Keyword(
                            className = "literal",

                            value = LITERALS
                        )
                    )
                )
            ),
            hljs.C_NUMBER_MODE,
            STRING
        )
    )
}
