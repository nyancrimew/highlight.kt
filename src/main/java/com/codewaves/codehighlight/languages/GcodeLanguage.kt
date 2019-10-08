package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = G-code (ISO 6983)
 Contributors = Adam Joseph Cook <adam.joseph.cook@gmail.com>
 Description = G-code syntax highlighter for Fanuc and other common CNC machine tool controls.
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class GcodeLanguage : LanguageBuilder  {
    val GCODE_IDENT_RE = "[A-Z_][A-Z0-9_.]*";
    val GCODE_CLOSE_RE = "\\%";
    val GCODE_KEYWORDS = "IF DO WHILE ENDWHILE CALL ENDIF SUB ENDSUB GOTO REPEAT ENDREPEAT EQ LT GT NE GE LE OR XOR";
    val GCODE_START = Mode(
        className = "meta",

        begin = "(listOf(O))(listOf(0-9]+)"
    );
    val GCODE_CODE = listOf(
        hljs.C_LINE_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.COMMENT("""\(""",
 """\)"""),
        hljs.inherit(hljs.C_NUMBER_MODE, Mode(begin = "(listOf(-+]?(listOf(0-9]*\\.?[0-9]+\\.?))|" +
 hljs.C_NUMBER_RE)),
        hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
        hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
        Mode(
            className = "name",

            begin = "(listOf(G))(listOf(0-9]+\\.?[0-9]?)"
        ),
        Mode(
            className = "name",

            begin = "(listOf(M))(listOf(0-9]+\\.?[0-9]?)"
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

            end = "(listOf(-+]?(listOf(0-9]*\\.?[0-9]+\\.?))(\\))"
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
    );

    override fun build() = Mode(
        aliases = listOf("nc")
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
        ) + GCODE_CODE)
    );
}
