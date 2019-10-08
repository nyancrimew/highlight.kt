package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = TP
Author = Jay Strybis <jay.strybis@gmail.com>
Description = FANUC TP programming language (TPP).
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class TpLanguage : LanguageBuilder {
    val TPID = Mode(
        className = "number",

        begin = "[1-9][0-9]*",
        /* no leading zeros */
        relevance = 0
    )
    val TPLABEL = Mode(
        className = "symbol",

        begin = ":[^\\]]+"
    )
    val TPDATA = Mode(
        className = "built_in",

        begin = "(AR|P|PAYLOAD|PR|R|SR|RSR|LBL|VR|UALM|MESSAGE|UTOOL|UFRAME|TIMER|TIMER_OVERFLOW|JOINT_MAX_SPEED|RESUME_PROG|DIAG_REC)\\[",

        end = "\\]",

        contains = listOf(

            TPID,
            TPLABEL
        )
    )
    val TPIO = Mode(
        className = "built_in",

        begin = "(AI|AO|DI|DO|F|RI|RO|UI|UO|GI|GO|SI|SO)\\[",

        end = "\\]",

        contains = listOf(

            TPID,
            hljs.QUOTE_STRING_MODE, /* for pos section at bottom */
            TPLABEL
        )
    )

    override fun build() = Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "ABORT ACC ADJUST AND AP_LD BREAK CALL CNT COL CONDITION CONFIG DA DB DIV DETECT ELSE END ENDFOR ERR_NUM ERROR_PROG FINE FOR GP GUARD INC IF JMP LINEAR_MAX_SPEED LOCK MOD MONITOR OFFSET Offset OR OVERRIDE PAUSE PREG PTH RT_LD RUN SELECT SKIP Skip TA TB TO TOOL_OFFSET Tool_Offset UF UT UFRAME_NUM UTOOL_NUM UNLOCK WAIT X Y Z W P R STRLEN SUBSTR FINDSTR VOFFSET PROG ATTR MN POS"
            ),
            Keyword(
                className = "literal",

                value = "ON OFF max_speed LPOS JPOS ENABLE DISABLE START STOP RESET"
            )
        ),
        contains = listOf(
            TPDATA,
            TPIO,
            Mode(
                className = "keyword",

                begin = "/(PROG|ATTR|MN|POS|END)\\b"
            ),
            Mode(
                /* this is for cases like ,CALL */
                className = "keyword",

                begin = "(CALL|RUN|POINT_LOGIC|LBL)\\b"
            ),
            Mode(
        /* this is for cases like CNT100 where the default lexemes do not
         * separate the keyword and the number */
                className = "keyword",

                begin = "\\b(ACC|CNT|Skip|Offset|PSPD|RT_LD|AP_LD|Tool_Offset)"
            ),
            Mode(
                /* to catch numbers that do not have a word boundary on the left */
                className = "number",

                begin = "\\d+(sec|msec|mm/sec|cm/min|inch/min|deg/sec|mm|in|cm)?\\b",

                relevance = 0
            ),
            hljs.COMMENT(
                "//",
                "[;\$]"
            ),
            hljs.COMMENT(
                "!",
                "[;\$]"
            ),
            hljs.COMMENT(
                "--eg:",
                "\$"
            ),
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "string",

                begin = "\'",

                end = "\'"
            ),
            hljs.C_NUMBER_MODE,
            Mode(
                className = "variable",

                begin = "\\$[A-Za-z0-9_]+"
            )
        )
    )
}
