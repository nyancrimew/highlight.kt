package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = QML
Requires = javascript.js, xml.js
Author = John Foster <jfoster@esri.com>
Description = Syntax highlighting for the Qt Quick QML scripting language, based mostly off
             the JavaScript parser.
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/qml.js MD5 <b23ca8ed762deb36f3865ec2788e1ff0>
 */
internal fun qml(): Mode {
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value = "in of on if for while finally var new function do return void else break catch instanceof with throw case default try this switch continue typeof delete let yield const export super debugger as async await import"
        ),
        Keyword(
            className = "literal",
            value = "true false null undefined NaN Infinity"
        ),
        Keyword(
            className = "built_in",
            value = "eval isFinite isNaN parseFloat parseInt decodeURI decodeURIComponent encodeURI encodeURIComponent escape unescape Object Function Boolean Error EvalError InternalError RangeError ReferenceError StopIteration SyntaxError TypeError URIError Number Math Date String RegExp Array Float32Array Float64Array Int16Array Int32Array Int8Array Uint16Array Uint32Array Uint8Array Uint8ClampedArray ArrayBuffer DataView JSON Intl arguments require module console window document Symbol Set Map WeakSet WeakMap Proxy Reflect Behavior bool color coordinate date double enumeration font geocircle georectangle geoshape int list matrix4x4 parent point quaternion real rect size string url variant vector2d vector3d vector4dPromise"
        )
    )
    var QML_IDENT_RE = "[a-zA-Z_][a-zA-Z0-9\\._]*"
    // Isolate property statements. Ends at a :, =, ;, ,, a comment or end of line.
    // Use property class.
    var PROPERTY = Mode(
        className = "keyword",
        begin = "\\bproperty\\b",
        starts = Mode(
            className = "string",
            end = "(:|=|;|,|//|/\\*|\$)",
            returnEnd = true
        )
    )
    // Isolate signal statements. Ends at a ) a comment or end of line.
    // Use property class.
    var SIGNAL = Mode(
        className = "keyword",
        begin = "\\bsignal\\b",
        starts = Mode(
            className = "string",
            end = "(\\(|:|=|;|,|//|/\\*|\$)",
            returnEnd = true
        )
    )
    // id: is special in QML. When we see id: we want to mark the id: as attribute and
    // emphasize the token following.
    var ID_ID = Mode(
        className = "attribute",
        begin = "\\bid\\s*:",
        starts = Mode(
            className = "string",
            end = QML_IDENT_RE,
            returnEnd = false
        )
    )
    // Find QML object attribute. An attribute is a QML identifier followed by :.
    // Unfortunately it's hard to know where it ends, as it may contain scalars,
    // objects, object definitions, or javascript. The true end is either when the parent
    // ends or the next attribute is detected.
    var QML_ATTRIBUTE = Mode(
        begin = QML_IDENT_RE + "\\s*:",
        returnBegin = true,
        contains = listOf(
            Mode(
                className = "attribute",
                begin = QML_IDENT_RE,
                end = "\\s*:",
                excludeEnd = true,
                relevance = 0
            )
        ),
        relevance = 0
    )
    // Find QML object. A QML object is a QML identifier followed by { and ends at the matching }.
    // All we really care about is finding IDENT followed by { and just mark up the IDENT and ignore the {.
    var QML_OBJECT = Mode(
        begin = QML_IDENT_RE + "\\s*{",
        end = "{",
        returnBegin = true,
        relevance = 0,
        contains = listOf(
            hljs.inherit(hljs.TITLE_MODE, Mode(begin = QML_IDENT_RE))
        )
    )
    return Mode(
        aliases = listOf("qt"),
        case_insensitive = false,
        keywords = keywords(KEYWORDS),
        contains = listOf(
            Mode(
                className = "meta",
                begin =
                    """^\s*["\"]use (strict|asm)[""]"""
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(// template string
                className = "string",
                begin = "`",
                end = "`",
                contains = listOf(
                    hljs.BACKSLASH_ESCAPE,
                    Mode(
                        className = "subst",
                        begin = "\\${'$'}\\{",
                        end = "\\}"
                    )
                )
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "number",
                variants = listOf(
                    Mode(begin = "\\b(0[bB][01]+)"),
                    Mode(begin = "\\b(0[oO][0-7]+)"),
                    Mode(begin = hljs.C_NUMBER_RE)
                ),
                relevance = 0
            ),
            Mode(// "value" container
                begin = "(\" + hljs.RE_STARTERS_RE + \"|\\b(case|return|throw)\\b)\\s*",
                keywords = keywords("return throw case"),
                contains = listOf(
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE,
                    hljs.REGEXP_MODE,
                    Mode(// E4X / JSX
                        begin =
                            """<""",
                        end =
                            """>\s*[);\]]""",
                        relevance = 0,
                        subLanguage = "xml"
                    )
                ),
                relevance = 0
            ),
            SIGNAL,
            PROPERTY,
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end =
                    """\{""",
                excludeEnd = true,
                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin =
                            """[A-Za-z${'$'}_][0-9A-Za-z${'$'}_]*""")),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        excludeBegin = true,
                        excludeEnd = true,
                        contains = listOf(
                            hljs.C_LINE_COMMENT_MODE,
                            hljs.C_BLOCK_COMMENT_MODE
                        )
                    )
                ),
                illegal =
                    """\[|%"""
            ),
            Mode(
                begin = "\\." + hljs.IDENT_RE,
                relevance = 0 // hack: prevents detection of keywords after dots
            ),
            ID_ID,
            QML_ATTRIBUTE,
            QML_OBJECT
        ),
        illegal =
            """#"""
    )
}
