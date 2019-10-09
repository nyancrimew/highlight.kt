package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Device Tree
Description = *.dts files used in the Linux kernel
Author = Martin Braun <martin.braun@ettus.com>, Moritz Fischer <moritz.fischer@ettus.com>
Category = config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/dts.js MD5 <575328d7b4ccad223f396141689c8121>
 */
internal fun dts(): Mode {
    var STRINGS = Mode(
        className = "string",
        variants = listOf(
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(begin = "((u8?|U)|L)?\"")),
            Mode(
                begin = "(u8?|U)?R\"",
                end = "\"",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                begin = "'\\\\?.",
                end = "'",
                illegal = "."
            )
        )
    )
    var NUMBERS = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(\\d+(\\.\\d*)?|\\.\\d+)(u|U|l|L|ul|UL|f|F)"),
            Mode(begin = hljs.C_NUMBER_RE)
        ),
        relevance = 0
    )
    var PREPROCESSOR = Mode(
        className = "meta",
        begin = "#",
        end = "\$",
        keywords = listOf(
            Keyword(
                className = "meta-keyword",
                value = "if else elif endif define undef ifdef ifndef"
            )
        ),
        contains = listOf(
            Mode(
                begin =
                    """\\\n""",
                relevance = 0
            ),
            Mode(
                beginKeywords = keywords("include"),
                end = "\${'\$'}",
                keywords = listOf(
                    Keyword(
                        className = "meta-keyword",
                        value = "include"
                    )
                ),
                contains = listOf(
                    hljs.inherit(STRINGS, Mode(className = "meta-string")),
                    Mode(
                        className = "meta-string",
                        begin = "<",
                        end = ">",
                        illegal = "\\n"
                    )
                )
            ),
            STRINGS,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE
        )
    )
    var DTS_REFERENCE = Mode(
        className = "variable",
        begin = "\\&[a-z\\d_]*\\b"
    )
    var DTS_KEYWORD = Mode(
        className = "meta-keyword",
        begin = "/[a-z][a-z\\d-]*/"
    )
    var DTS_LABEL = Mode(
        className = "symbol",
        begin = "^\\s*[a-zA-Z_][a-zA-Z\\d_]*:"
    )
    var DTS_CELL_PROPERTY = Mode(
        className = "params",
        begin = "<",
        end = ">",
        contains = listOf(
            NUMBERS,
            DTS_REFERENCE
        )
    )
    var DTS_NODE = Mode(
        className = "class",
        begin =
            """[a-zA-Z_][a-zA-Z\d_@]*\s{""",
        end =
            """[{;=]""",
        returnBegin = true,
        excludeEnd = true
    )
    var DTS_ROOT_NODE = Mode(
        className = "class",
        begin = "/\\s*{",
        end = ");",
        relevance = 10,
        contains = listOf(
            DTS_REFERENCE,
            DTS_KEYWORD,
            DTS_LABEL,
            DTS_NODE,
            DTS_CELL_PROPERTY,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMBERS,
            STRINGS
        )
    )
    return Mode(
        keywords = keywords(""),
        contains = listOf(
            DTS_ROOT_NODE,
            DTS_REFERENCE,
            DTS_KEYWORD,
            DTS_LABEL,
            DTS_NODE,
            DTS_CELL_PROPERTY,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMBERS,
            STRINGS,
            PREPROCESSOR,
            Mode(
                begin = hljs.IDENT_RE + "::",
                keywords = keywords("")
            )
        )
    )
}
