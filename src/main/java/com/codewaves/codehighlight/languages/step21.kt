package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = STEP Part 21
Contributors = Adam Joseph Cook <adam.joseph.cook@gmail.com>
Description = Syntax highlighter for STEP Part 21 files (ISO 10303-21).
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/step21.js MD5 <3413303ad6db1dfbbc2a658f91927c18>
 */
internal fun step21(): Mode {
    var STEP21_IDENT_RE = "[A-Z_][A-Z0-9_.]*"
    var STEP21_KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value = "HEADER ENDSEC DATA"
        )
    )
    var STEP21_START = Mode(
        className = "meta",
        begin = "ISO-10303-21;",
        relevance = 10
    )
    var STEP21_CLOSE = Mode(
        className = "meta",
        begin = "END-ISO-10303-21;",
        relevance = 10
    )
    return Mode(
        aliases = listOf("p21\", \"step\", \"stp"),
        case_insensitive = true, // STEP 21 is case insensitive in theory, in practice all non-comments are capitalized.
        lexemes = STEP21_IDENT_RE,
        keywords = keywords(STEP21_KEYWORDS),
        contains = listOf(
            STEP21_START,
            STEP21_CLOSE,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.COMMENT("/\\*\\*!\", \"\\*/"),
            hljs.C_NUMBER_MODE,
            hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
            Mode(
                className = "string",
                begin = "'",
                end = "'"
            ),
            Mode(
                className = "symbol",
                variants = listOf(
                    Mode(
                        begin = "#",
                        end = "\\d+",
                        illegal = "\\W"
                    )
                )
            )
        )
    )
}
