package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = YAML
Author = Stefan Wienert <stwienert@gmail.com>
Requires = ruby.js
Description = YAML (Yet Another Markdown Language)
Category = common, config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/yaml.js MD5 <0abdf8afe4f1a0a5882e3ad141664102>
 */
internal fun yaml(): Mode {
    var LITERALS = "true false yes no null"
    // Define keys as starting with a word character 
    // ...containing word chars, spaces, colons, forward-slashes, hyphens and periods
    // ...and ending with a colon followed immediately by a space, tab or newline.
    // The YAML spec allows for much more than this, but this covers most use-cases.
    var KEY = Mode(
        className = "attr",
        variants = listOf(
            // TODO: remove |$ hack when we have proper look-ahead support
            Mode(begin = "\\w[\\w:\\/.-]*:(?=[ \t]|\$)"),
            Mode(begin = "\"\\w[\\w:\\/.-]*\":(?=[ \t]|\$)"), // double quoted keys
            Mode(begin = "'\\w[\\w:\\/.-]*':(?=[ \t]|\$)") // single quoted keys
        )
    )
    var TEMPLATE_VARIABLES = Mode(
        className = "template-variable",
        variants = listOf(
            Mode(
                begin = "\\{\\{",
                end = "\\}\\}"
            ),
            // jinja templates Ansible
            Mode(
                begin = "%\\{",
                end = "\\}"
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
        aliases = listOf(
            "yml",
            "YAML",
            "yaml"
        ),
        contains = listOf(
            KEY,
            Mode(
                className = "meta",
                begin = "^---\\s*\$",
                relevance = 10
            ),
            Mode(// multi line string
                className = "string",
                begin = "[\\|>] *\$",
                returnEnd = true,
                contains = STRING.contains,
                // very simple termination: next hash key
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
                    hljs.UNDERSCORE_IDENT_RE + "\\\$"
            ),
            Mode(// fragment reference *ref
                className = "meta",
                begin = "\\*" +
                    hljs.UNDERSCORE_IDENT_RE + "\\\$"
            ),
            Mode(// array listing
                className = "bullet",
                begin = "^ *-",
                relevance = 0
            ),
            hljs.HASH_COMMENT_MODE,
            Mode(
                beginKeywords = keywords(LITERALS),
                keywords = keyword(
                        className = "literal",
                        value = LITERALS
                )
            ),
            hljs.C_NUMBER_MODE,
            STRING
        )
    )
}
