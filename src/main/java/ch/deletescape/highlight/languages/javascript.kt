package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = JavaScript
Category = common, scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/javascript.js MD5 <c8e9a3f0d9a397cf8d61a3e1c80daea7>
 */
internal fun javascript(): Mode {
    var IDENT_RE = "[A-Za-z\$_][0-9A-Za-z\$_]*"
    var KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "in of if for while finally var new function do return void else break catch instanceof with throw case default try this switch continue typeof delete let yield const export super debugger as async await static import from as"
        ),
        keyword(
            className = "literal",
            value =
                "true false null undefined NaN Infinity"
        ),
        keyword(
            className = "built_in",
            value =
                "eval isFinite isNaN parseFloat parseInt decodeURI decodeURIComponent encodeURI encodeURIComponent escape unescape Object Function Boolean Error EvalError InternalError RangeError ReferenceError StopIteration SyntaxError TypeError URIError Number Math Date String RegExp Array Float32Array Float64Array Int16Array Int32Array Int8Array Uint16Array Uint32Array Uint8Array Uint8ClampedArray ArrayBuffer DataView JSON Intl arguments require module console window document Symbol Set Map WeakSet WeakMap Proxy Reflect Promise"
        )
    ).flatten()
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
        begin = "\\\$\\{",
        end = "\\\\}",
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
    var PARAMS_CONTAINS = SUBST.contains + listOf(
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.C_LINE_COMMENT_MODE
    )
    return Mode(
        aliases = listOf(
            "js",
            "jsx"
        ),
        keywords = keywords(KEYWORDS),
        contains = listOf(
            Mode(
                className = "meta",
                relevance = 10,
                begin =
                    """^\s*['\"]use (strict|asm)['"]"""
            ),
            Mode(
                className = "meta",
                begin =
                    """^#!""",
                end =
                    """${'$'}"""
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            HTML_TEMPLATE,
            CSS_TEMPLATE,
            TEMPLATE_STRING,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMBER,
            Mode(// object attr container
                begin =
                    """[{,\n]\s*""",
                relevance = 0,
                contains = listOf(
                    Mode(
                        begin = IDENT_RE + "\\s*:",
                        returnBegin = true,
                        relevance = 0,
                        contains = listOf(
                            Mode(
                                className = "attr",
                                begin = IDENT_RE,
                                relevance = 0
                            )
                        )
                    )
                )
            ),
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
                            IDENT_RE + ")\\s*=>",
                        returnBegin = true,
                        end = "\\s*=>",
                        contains = listOf(
                            Mode(
                                className = "params",
                                variants = listOf(
                                    Mode(
                                        begin = IDENT_RE
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
                                        contains = PARAMS_CONTAINS
                                    )
                                )
                            )
                        )
                    ),
                    Mode(
                        className = "",
                        begin =
                            """\s""",
                        end =
                            """\s*""",
                        skip = true
                    ),
                    Mode(// E4X / JSX
                        begin =
                            """<""",
                        end =
                            """(\/[A-Za-z0-9\\._ = -]+|[A-Za-z0-9\\._ = -]+\/)>""",
                        subLanguage = "xml",
                        contains = listOf(
                            Mode(
                                begin =
                                    """<[A-Za-z0-9\\._ = -]+\s*\/>""",
                                skip = true
                            ),
                            Mode(
                                begin =
                                    """<[A-Za-z0-9\\._ = -]+""",
                                end =
                                    """(\/[A-Za-z0-9\\._ = -]+|[A-Za-z0-9\\._ = -]+\/)>""",
                                skip = true,
                                contains = listOf(
                                    Mode(
                                        begin =
                                            """<[A-Za-z0-9\\._ = -]+\s*\/>""",
                                        skip = true
                                    ),
                                    hljs.SELF
                                )
                            )
                        )
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end =
                    """\{""",
                excludeEnd = true,
                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = IDENT_RE)),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        excludeBegin = true,
                        excludeEnd = true,
                        contains = PARAMS_CONTAINS
                    )
                ),
                illegal =
                    """\[|%"""
            ),
            Mode(
                begin =
                    """\${'$'}[(.]""" // relevance booster for a pattern common to JS libs: `${'$'}(something)` and `${'$'}.something`
            ),
            hljs.METHOD_GUARD,
            Mode(// ES6 class
                className = "class",
                beginKeywords = keywords("class"),
                end =
                    """[{;=]""",
                excludeEnd = true,
                illegal =
                    """[:"\[\]]""",
                contains = listOf(
                    Mode(beginKeywords = keywords("extends")),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                beginKeywords = keywords("constructor get set"),
                end =
                    """\{""",
                excludeEnd = true
            )
        ),
        illegal =
            """#(?!!)"""
    )
}
