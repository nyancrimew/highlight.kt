package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = CoffeeScript
Author = Dmytrii Nagirniak <dnagir@gmail.com>
Contributors = Oleg Efimov <efimovov@gmail.com>, Cédric Néhémie <cedric.nehemie@gmail.com>
Description = CoffeeScript is a programming language that transcompiles to JavaScript. For info about language see http:"""/coffeescript.org"""
Category = common, scripting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CoffeescriptLanguage : LanguageBuilder {
    val KEYWORDS = keywordsJson(
        """
keyword =       // JS keywords
      "in if for while finally new do return else break catch instanceof throw try this switch continue typeof delete debugger super yield import export from as default await then unless until loop of by when and or is isnt not",
literal =       // JS literals
      "true false null undefined yes no on off",
built_in = "npm require console print module global window document"""".trimIndent()
    )
    val JS_IDENT_RE = "[A-Za-z\$_][0-9A-Za-z\$_]*"
    val SUBST = Mode(
        className = "subst",
        begin = """#\{""",
        end = """}""",
        keywords = KEYWORDS
    )
    val EXPRESSIONS = listOf(
        hljs.BINARY_NUMBER_MODE,
        hljs.inherit(
            hljs.C_NUMBER_MODE, Mode(
                starts = Mode(
                    end = "(\\s*\"\"\")?",
                    relevance = 0
                )
            )
        ), //""" a number tries to eat the following slash to prevent treating it as a regexp
        Mode(
            className = "string",
            variants = listOf(
                Mode(
                    begin = """'''""",
                    end = """'''""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin = """'""",
                    end = """'""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin = """"""""",
                    end = """"""""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
                ),
                Mode(
                    begin = """"""",
                    end = """"""",
                    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
                )
            )
        ),
        Mode(
            className = "regexp",
            variants = listOf(
                Mode(
                    begin = "///",
                    end = "///",
                    contains = listOf(SUBST, hljs.HASH_COMMENT_MODE)
                ),
                Mode(
                    begin = "//[gim]*",
                    relevance = 0
                ),
                Mode(
                    className = "regex", // regex can't start with space to parse x """ 2 / 3 as two divisions
                    // regex can't start with *, and it supports an "illegal" in the main mode
                    begin = """\/(?![ *))(\\\/|.)*?\/[gim]*(?=\W|${'$'})"""
                )
            )
        ),
        Mode(
            begin = "@javascript",
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

    val TITLE = hljs.inherit(hljs.TITLE_MODE, Mode(begin = JS_IDENT_RE))
    val PARAMS_RE = "(\\(.*\\))?\\s*\\B[-=]>"
    val PARAMS = Mode(
        className = "params",
        begin = "\\(listOf(^\\(]",
        returnBegin = true,
        /* We need another contained nameless mode to not have every nested
        pair of parens to be called "params" */
        contains = listOf(
            Mode(
                begin = """\(""",
                end = """\)""",
                keywords = KEYWORDS,
                contains = EXPRESSIONS
            )
        )
    )

    override fun build() = Mode(
        aliases = listOf("coffee", "cson", "iced"),
        keywords = KEYWORDS,
        illegal = """\/\*""",
        contains = EXPRESSIONS + listOf(
            hljs.COMMENT("###", "###"),
            hljs.HASH_COMMENT_MODE,
            Mode(
                className = "function",
                begin = "^\\s*\\s*=\\s*[-=]>",
                returnBegin = true,
                contains = listOf(TITLE, PARAMS)
            ),
            Mode(
                // anonymous function start
                begin = """[:\(,=]\s*""",
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
                illegal = """[:="\[\]]""",
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends"),
                        endsWithParent = true,
                        illegal = """[:="\[\]]""",
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
