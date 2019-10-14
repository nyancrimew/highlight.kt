package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Haxe
Author = Christopher Kaster <ikasoki@gmail.com> (Based on the actionscript.js language file by Alexander Myadzel)
Contributors = Kenton Hamaluik <kentonh@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/haxe.js MD5 <c6cfecdda00f7f9cd16d0640c5a4fb00>
 */
internal fun haxe(): Mode {
    var IDENT_RE = "[a-zA-Z_\$][a-zA-Z0-9_\$]*"
    var IDENT_FUNC_RETURN_TYPE_RE = "([*]|[a-zA-Z_\$][a-zA-Z0-9_\$]*)"
    var HAXE_BASIC_TYPES = "Int Float String Bool Dynamic Void Array "
    return Mode(
        aliases = listOf("hx"),
        keywords = listOf(
            keyword(
                className = "keyword",
                value = "break case cast catch continue default do dynamic else enum extern for function here if import in inline never new override package private get set public return static super switch this throw trace try typedef untyped using var while " +
                    HAXE_BASIC_TYPES
            ),
            keyword(
                className = "built_in",
                value =
                    "trace this"
            ),
            keyword(
                className = "literal",
                value =
                    "true false null _"
            )
        ).flatten(),
        contains = listOf(
            Mode(
                className = "string",
                // interpolate-able strings
                begin = "'",
                end = "'",
                contains = listOf(
                    hljs.BACKSLASH_ESCAPE,
                    Mode(
                        className = "subst",
                        // interpolation
                        begin = "\\\$\\{",
                        end = "\\}"
                    ),
                    Mode(
                        className = "subst",
                        // interpolation
                        begin = "\\\$",
                        end = "\\W\\}"
                    )
                )
            ),
            hljs.QUOTE_STRING_MODE,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",
                // compiler meta
                begin = "@:",
                end = "$"
            ),
            Mode(
                className = "meta",
                // compiler conditionals
                begin = "#",
                end = "$",
                keywords = keyword(
                        className = "meta-keyword",
                        value = "if else elseif end error"
                    )
                ),
            Mode(
                className = "type",
                // function types
                begin = ":[ \t]*",
                end = "[^A-Za-z0-9_ \t\\->]",
                excludeBegin = true,
                excludeEnd = true,
                relevance = 0
            ),
            Mode(
                className = "type",
                // types
                begin = ":[ \t]*",
                end = "\\W",
                excludeBegin = true,
                excludeEnd = true
            ),
            Mode(
                className = "type",
                // instantiation
                begin = "new *",
                end = "\\W",
                excludeBegin = true,
                excludeEnd = true
            ),
            Mode(
                className = "class",
                // enums
                beginKeywords = keywords("enum"),
                end = "\\{",
                contains = listOf(
                    hljs.TITLE_MODE
                )
            ),
            Mode(
                className = "class",
                // abstracts
                beginKeywords = keywords("abstract"),
                end = "[\\\\{\\\$]",
                contains = listOf(
                    Mode(
                        className = "type",
                        begin = "\\(",
                        end = "\\)",
                        excludeBegin = true,
                        excludeEnd = true
                    ),
                    Mode(
                        className = "type",
                        begin = "from +",
                        end = "\\W",
                        excludeBegin = true,
                        excludeEnd = true
                    ),
                    Mode(
                        className = "type",
                        begin = "to +",
                        end = "\\W",
                        excludeBegin = true,
                        excludeEnd = true
                    ),
                    hljs.TITLE_MODE
                ),
                keywords = keyword(
                        className = "keyword",
                        value = "abstract from to"
                    )
            ),
            Mode(
                className = "class",
                // classes
                begin = "\\b(class|interface) +",
                end = "[\\\\{\\\$]",
                excludeEnd = true,
                keywords = keywords("class interface"),
                contains = listOf(
                    Mode(
                        className = "keyword",
                        begin = "\\b(extends|implements) +",
                        keywords = keywords("extends implements"),
                        contains = listOf(
                            Mode(
                                className = "type",
                                begin = hljs.IDENT_RE,
                                relevance = 0
                            )
                        )
                    ),
                    hljs.TITLE_MODE
                )
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end = "\\(",
                excludeEnd = true,
                illegal = "\\S",
                contains = listOf(
                    hljs.TITLE_MODE
                )
            )
        ),
        illegal =
            """<\/"""
    )
}
