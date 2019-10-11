package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keywords

/*
Language = CoffeeScript
Author = Dmytrii Nagirniak <dnagir@gmail.com>
Contributors = Oleg Efimov <efimovov@gmail.com>, Cédric Néhémie <cedric.nehemie@gmail.com>
Description = CoffeeScript is a programming language that transcompiles to JavaScript. For info about language see http = //coffeescript.org/
Category = common, scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/coffeescript.js MD5 <c636bce2955fe93d354d5f371a864e1f>
 */
internal fun coffeescript(): Mode {
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                // JS keywords
                "in if for while finally new do return else break catch instanceof throw try this switch continue typeof delete debugger super yield import export from as default await then unless until loop of by when and or is isnt not"
        ),
        Keyword(
            className = "literal",
            value =
                // JS literals
                "true false null undefined yes no on off"
        ),
        Keyword(
            className = "built_in",
            value =
                "npm require console print module global window document"
        )
    )
    var JS_IDENT_RE = "[A-Za-z\$_][0-9A-Za-z\$_]*"
    var SUBST = Mode(
        className = "subst",
        begin =
            """#\{""",
        end =
            """}""",
        keywords = keywords(KEYWORDS)
    )
    var EXPRESSIONS = listOf(
        hljs.BINARY_NUMBER_MODE,
        hljs.inherit(
            hljs.C_NUMBER_MODE,
            Mode(
                starts = Mode(
                    end = "(\\s*/)?",
                    relevance = 0
                )
            )
        ),
        // a number tries to eat the following slash to prevent treating it as a regexp
        Mode(
            className = "string",
            variants = listOf(
                Mode(
                    begin =
                        """'''""",
                    end =
                        """'''""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin =
                        """'""",
                    end =
                        """'""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin =
                        """"""""",
                    end =
                        """"""""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
                ),
                Mode(
                    begin =
                        """"""",
                    end =
                        """"""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
                )
            )
        ),
        Mode(
            className = "regexp",
            variants = listOf(
                Mode(
                    begin = "///', end: '///",
                    contains = listOf(SUBST, hljs.HASH_COMMENT_MODE)
                ),
                Mode(
                    begin = "//[gim]*",
                    relevance = 0
                ),
                Mode(
                    // regex can't start with space to parse x """ 2 """ 3 as two divisions
                    // regex can't start with *, and it supports an "illegal" in the main mode
                    begin =
                        """\/(?![ *))(\\\/|.)*?\/[gim]*(?=\W|${'$'})"""
                )
            )
        ),
        Mode(
            begin = "@" +
                JS_IDENT_RE // relevance booster
        ),
        Mode(
            subLanguage = "javascript",
            excludeBegin = true,
            excludeEnd = true,
            variants = listOf(
                Mode(
                    begin = "```",
                    end = "```"
                ),
                Mode(
                    begin = "`",
                    end = "`"
                )
            )
        )
    )
    SUBST.contains = EXPRESSIONS
    var TITLE = hljs.inherit(hljs.TITLE_MODE, Mode(begin = JS_IDENT_RE))
    var PARAMS_RE = "(\\(.*\\))?\\s*\\B[-=]>"
    var PARAMS = Mode(
        className = "params",
        begin = "\\([^\\(]",
        returnBegin = true,
        /* We need another contained nameless mode to not have every nested
        pair of parens to be called "params" */
        contains = listOf(
            Mode(
                begin =
                    """\(""",
                end =
                    """\)""",
                keywords = keywords(KEYWORDS),
                contains = listOf(hljs.SELF) + EXPRESSIONS
            )
        )
    )
    return Mode(
        aliases = listOf(
            "coffee",
            "cson",
            "iced"
        ),
        keywords = keywords(KEYWORDS),
        illegal =
            """\/\*""",
        contains = EXPRESSIONS + listOf(
            hljs.COMMENT(
                "###",
                "###",
                hljs.HASH_COMMENT_MODE
            ),
            Mode(
                className = "function",
                begin = "^\\s*" +
                    JS_IDENT_RE + "\\s*=\\s*" +
                    PARAMS_RE,
                end = "[-=]>",
                returnBegin = true,
                contains = listOf(TITLE, PARAMS)
            ),
            Mode(
                // anonymous function start
                begin =
                    """[:\(,=]\s*""",
                relevance = 0,
                contains = listOf(
                    Mode(
                        className = "function",
                        begin = PARAMS_RE,
                        end = "[-=]>",
                        returnBegin = true,
                        contains = listOf(PARAMS)
                    )
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class"),
                end = "${'$'}",
                illegal =
                    """[:="\[\]]""",
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends"),
                        endsWithParent = true,
                        illegal =
                            """[:="\[\]]""",
                        contains = listOf(TITLE)
                    ),
                    TITLE
                )
            ),
            Mode(
                begin = JS_IDENT_RE + ":",
                end = ":",
                returnBegin = true,
                returnEnd = true,
                relevance = 0
            )
        )
    )
}
