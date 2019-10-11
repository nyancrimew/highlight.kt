package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = ActionScript
Author = Alexander Myadzel <myadzel@gmail.com>
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/actionscript.js MD5 <86cef88561ebaecf28bf89e4a87c0932>
 */
internal fun actionscript(): Mode {
    var IDENT_RE = "[a-zA-Z_\$][a-zA-Z0-9_\$]*"
    var IDENT_FUNC_RETURN_TYPE_RE = "([*]|[a-zA-Z_\$][a-zA-Z0-9_\$]*)"
    var AS3_REST_ARG_MODE = Mode(
        className = "rest_arg",
        begin = "[.]{3}",
        end = IDENT_RE,
        relevance = 10
    )
    return Mode(
        aliases = listOf("as"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "as break case catch class const continue default delete do dynamic each else extends final finally for function get if implements import in include instanceof interface internal is namespace native new override package private protected public return set static super switch this throw try typeof use var void while with"
            ),
            Keyword(
                className = "literal",
                value = "true false null undefined"
            )
        ),
        contains = listOf(
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "class",
                beginKeywords = keywords("package"),
                end = "\\{",
                contains = listOf(hljs.TITLE_MODE)
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end = "\\{",
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends implements")
                    ),
                    hljs.TITLE_MODE
                )
            ),
            Mode(
                className = "meta",
                beginKeywords = keywords("import include"),
                end = ";",
                keywords = listOf(
                    Keyword(
                        className = "meta-keyword",
                        value = "import include"
                    )
                )
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end = "[\\{;]",
                excludeEnd = true,
                illegal = "\\S",
                contains = listOf(
                    hljs.TITLE_MODE,
                    Mode(
                        className = "params",
                        begin = "\\(",
                        end = "\\)",
                        contains = listOf(
                            hljs.APOS_STRING_MODE,
                            hljs.QUOTE_STRING_MODE,
                            hljs.C_LINE_COMMENT_MODE,
                            hljs.C_BLOCK_COMMENT_MODE,
                            AS3_REST_ARG_MODE
                        )
                    ),
                    Mode(
                        begin = ":\\s*" +
                            IDENT_FUNC_RETURN_TYPE_RE
                    )
                )
            ),
            hljs.METHOD_GUARD
        ),
        illegal =
            """#"""
    )
}
