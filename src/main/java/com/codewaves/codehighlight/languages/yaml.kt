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
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/yaml.js MD5 <c9373df2e5c9649d4799ef9f10b1e718>
 */
internal fun yaml(): Mode {
    var LITERALS = "true false yes no null"
    var keyPrefix = "^[ \\-]*"
    var keyName = "[a-zA-Z_][\\w\\-]*"
    var KEY = Mode(
        className = "attr",
        variants = listOf(
            Mode(begin = keyPrefix + keyName + ":"),
            Mode(begin = keyPrefix + "\"\" + keyName + \"\":"),
            Mode(begin = keyPrefix + "'\" + keyName + \"':")
        )
    )
    var TEMPLATE_VARIABLES = Mode(
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
    var STRING = Mode(
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
    return Mode(
        case_insensitive = true,
        aliases = listOf("yml\", \"YAML\", \"yaml"),
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
                // very simple termination: next hash key
                end = KEY.variants[0]
                    .begin
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
                begin = "!" + hljs.UNDERSCORE_IDENT_RE
            ),
            Mode(// data type
                className = "type",
                begin = "!!" + hljs.UNDERSCORE_IDENT_RE
            ),
            Mode(// fragment id &ref
                className = "meta",
                begin = "&\" + hljs.UNDERSCORE_IDENT_RE + \"\$"
            ),
            Mode(// fragment reference *ref
                className = "meta",
                begin = "\\*\" + hljs.UNDERSCORE_IDENT_RE + \"\$"
            ),
            Mode(// array listing
                className = "bullet",
                begin = "^ *-",
                relevance = 0
            ),
            hljs.HASH_COMMENT_MODE,
            Mode(
                beginKeywords = keywords(LITERALS),
                keywords = listOf(
                    Keyword(
                        className = "literal",
                        value = LITERALS
                    )
                )
            ),
            hljs.C_NUMBER_MODE,
            STRING
        )
    )
}
