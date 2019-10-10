package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = TypeScript
Author = Panu Horsmalahti <panu.horsmalahti@iki.fi>
Contributors = Ike Ku <dempfi@yahoo.com>
Description = TypeScript is a strict superset of JavaScript
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/typescript.js MD5 <fd78a1cddcc5d5adabbc64dbdf00fd52>
 */
internal fun typescript(): Mode {
    var JS_IDENT_RE = "[A-Za-z\$_][0-9A-Za-z\$_]*"
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                "in if for while finally var new function do return void else break catch instanceof with throw case default try this switch continue typeof delete let yield const class public private protected get set super static implements enum export import declare type namespace abstract as from extends async await"
        ),
        Keyword(
            className = "literal",
            value =
                "true false null undefined NaN Infinity"
        ),
        Keyword(
            className = "built_in",
            value =
                "eval isFinite isNaN parseFloat parseInt decodeURI decodeURIComponent encodeURI encodeURIComponent escape unescape Object Function Boolean Error EvalError InternalError RangeError ReferenceError StopIteration SyntaxError TypeError URIError Number Math Date String RegExp Array Float32Array Float64Array Int16Array Int32Array Int8Array Uint16Array Uint32Array Uint8Array Uint8ClampedArray ArrayBuffer DataView JSON Intl arguments require module console window document any number boolean string void Promise"
        )
    )
    var DECORATOR = Mode(
        className = "meta",
        begin = "@" +
            JS_IDENT_RE
    )
    var ARGS =
        Mode(
            begin = "\\(",
            end =
                """\)""",
            keywords = keywords(KEYWORDS),
            contains = listOf(
                hljs.SELF,
                hljs.QUOTE_STRING_MODE,
                hljs.APOS_STRING_MODE,
                hljs.NUMBER_MODE
            )
        )
    var PARAMS = Mode(
        className = "params",
        begin =
            """\(""",
        end =
            """\)""",
        excludeBegin = true,
        excludeEnd = true,
        keywords = keywords(KEYWORDS),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            DECORATOR,
            ARGS
        )
    )
    var NUMBER = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0[bB][01]+)n?"),
            Mode(begin = "\\b(0[oO][0-7]+)n?"),
            Mode(begin = hljs.C_NUMBER_RE + "n?")
        ),
        relevance = 0
    )
    var SUBST = Mode(
        className = "subst",
        begin = "\\\${'$'}\\{",
        end = "\\}",
        keywords = keywords(KEYWORDS),
        contains = listOf() // defined later
    )
    var HTML_TEMPLATE = Mode(
        begin = "html`",
        end = "",
        starts = Mode(
            end = "`",
            returnEnd = false,
            contains = listOf(
                hljs.BACKSLASH_ESCAPE,
                SUBST
            ),
            subLanguage = "xml"
        )
    )
    var CSS_TEMPLATE = Mode(
        begin = "css`",
        end = "",
        starts = Mode(
            end = "`",
            returnEnd = false,
            contains = listOf(
                hljs.BACKSLASH_ESCAPE,
                SUBST
            ),
            subLanguage = "css"
        )
    )
    var TEMPLATE_STRING = Mode(
        className = "string",
        begin = "`",
        end = "`",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            SUBST
        )
    )
    SUBST.contains = listOf(
        hljs.APOS_STRING_MODE,
        hljs.QUOTE_STRING_MODE,
        HTML_TEMPLATE,
        CSS_TEMPLATE,
        TEMPLATE_STRING,
        NUMBER,
        hljs.REGEXP_MODE
    )

    return Mode(
        aliases = listOf("ts"),
        keywords = keywords(KEYWORDS),
        contains = listOf(
            Mode(
                className = "meta",
                begin =
                    """^\s*['\"]use strict['"]"""
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            HTML_TEMPLATE,
            CSS_TEMPLATE,
            TEMPLATE_STRING,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMBER,
            Mode(// "value" container
                begin = "(" +
                    hljs.RE_STARTERS_RE + "|\\b(case|return|throw)\\b)\\s*",
                keywords = keywords("return throw case"),
                contains = listOf(
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE,
                    hljs.REGEXP_MODE,
                    Mode(
                        className = "function",
                        begin = "(\\(.*?\\)|" +
                            hljs.IDENT_RE + ")\\s*=>",
                        returnBegin = true,
                        end = "\\s*=>",
                        contains = listOf(
                            Mode(
                                className = "params",
                                variants = listOf(
                                    Mode(
                                        begin = hljs.IDENT_RE
                                    ),
                                    Mode(
                                        begin =
                                            """\(\s*\)"""
                                    ),
                                    Mode(
                                        begin =
                                            """\(""",
                                        end =
                                            """\)""",
                                        excludeBegin = true,
                                        excludeEnd = true,
                                        keywords = keywords(KEYWORDS),
                                        contains = listOf(
                                            hljs.SELF,
                                            hljs.C_LINE_COMMENT_MODE,
                                            hljs.C_BLOCK_COMMENT_MODE
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "function",
                begin = "function",
                end =
                    """[\{;]""",
                excludeEnd = true,
                keywords = keywords(KEYWORDS),
                contains = listOf(
                    hljs.SELF,
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = JS_IDENT_RE)),
                    PARAMS
                ),
                illegal =
                    """%""",
                relevance = 0 // () => {} is more typical in TypeScript
            ),
            Mode(
                beginKeywords = keywords("constructor"),
                end =
                    """\{""",
                excludeEnd = true,
                contains = listOf(
                    hljs.SELF,
                    PARAMS
                )
            ),
            Mode(// prevent references like module.id from being higlighted as module definitions
                begin =
                    """module\.""",
                keywords = listOf(
                    Keyword(
                        className = "built_in",
                        value = "module"
                    )
                ),
                relevance = 0
            ),
            Mode(
                beginKeywords = keywords("module"),
                end =
                    """\{""",
                excludeEnd = true
            ),
            Mode(
                beginKeywords = keywords("interface"),
                end =
                    """\{""",
                excludeEnd = true,
                keywords = keywords("interface extends")
            ),
            Mode(
                begin =
                    """\${'$'}[(.]""" // relevance booster for a pattern common to JS libs: `$(something)` and `$.something`
            ),
            Mode(
                begin = "\\." +
                    hljs.IDENT_RE,
                relevance = 0 // hack: prevents detection of keywords after dots
            ),
            DECORATOR,
            ARGS
        )
    )
}
