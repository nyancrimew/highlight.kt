package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
 Language = G-code (ISO 6983)
 Contributors = Adam Joseph Cook <adam.joseph.cook@gmail.com>
 Description = G-code syntax highlighter for Fanuc and other common CNC machine tool controls.
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/gcode.js MD5 <5b9a284d41a4ab4a7a9a8b89a7e092ca>
 */
internal fun gcode(): Mode {
    var GCODE_IDENT_RE = "[A-Z_][A-Z0-9_.]*"
    var GCODE_CLOSE_RE = "\\%"
    var GCODE_KEYWORDS = "IF DO WHILE ENDWHILE CALL ENDIF SUB ENDSUB GOTO REPEAT ENDREPEAT EQ LT GT NE GE LE OR XOR"
    var GCODE_START = Mode(
        className = "meta",
        begin = "([O))([0-9]+)"
    )
    var GCODE_CODE = listOf(
        hljs.C_LINE_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.COMMENT("""\(""", """\)"""),
        hljs.inherit(hljs.C_NUMBER_MODE, Mode(begin = "([-+]?([0-9]*\\.?[0-9]+\\.?))|" + hljs.C_NUMBER_RE)),
        hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
        hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
        Mode(
            className = "name",
            begin = "([G))([0-9]+\\.?[0-9]?)"
        ),
        Mode(
            className = "name",
            begin = "([M))([0-9]+\\.?[0-9]?)"
        ),
        Mode(
            className = "attr",
            begin = "(VC|VS|#)",
            end = "(\\d+)"
        ),
        Mode(
            className = "attr",
            begin = "(VZOFX|VZOFY|VZOFZ)"
        ),
        Mode(
            className = "built_in",
            begin = "(ATAN|ABS|ACOS|ASIN|SIN|COS|EXP|FIX|FUP|ROUND|LN|TAN)(\\[)",
            end = "([-+]?([0-9]*\\.?[0-9]+\\.?))(\\))"
        ),
        Mode(
            className = "symbol",
            variants = listOf(
                Mode(
                    begin = "N",
                    end = "\\d+",
                    illegal = "\\W"
                )
            )
        )
    )
    return Mode(
        aliases = listOf("nc"),
        // Some implementations (CNC controls) of G-code are interoperable with uppercase and lowercase letters seamlessly.
        // However, most prefer all uppercase and uppercase is customary.
        case_insensitive = true,
        lexemes = GCODE_IDENT_RE,
        keywords = keywords(GCODE_KEYWORDS),
        contains = listOf(
            Mode(
                className = "meta",
                begin = GCODE_CLOSE_RE
            ),
            GCODE_START
        ) +
            GCODE_CODE
    )
}
