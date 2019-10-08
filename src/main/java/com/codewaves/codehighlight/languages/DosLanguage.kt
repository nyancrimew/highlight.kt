package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = DOS .bat
Author = Alexander Makarov <sam@rmcreative.ru>
Contributors = Anton Kochkov <anton.kochkov@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DosLanguage : LanguageBuilder {
    val COMMENT = hljs.COMMENT(
        """^\s*@?rem\b""",
        """${'$'}""",

        Mode(
            relevance = 10
        )
    )
    val LABEL = Mode(
        className = "symbol",

        begin = "^\\s*[A-Za-z._?][A-Za-z0-9_\$#@~.?]*(:|\\s+label)",

        relevance = 0
    )
    override fun build() = Mode(
        aliases = listOf(
            "bat",
            "cmd"
        ),
        case_insensitive = true,
        illegal =
            """\/\*""",

        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "if else goto for in do call exit not exist errorlevel defined equ neq lss leq gtr geq"
            ),
            Keyword(
                className = "built_in",

                value = "prn nul lpt3 lpt2 lpt1 con com4 com3 com2 com1 aux shift cd dir echo setlocal endlocal set pause copy append assoc at attrib break cacls cd chcp chdir chkdsk chkntfs cls cmd color comp compact convert date dir diskcomp diskcopy doskey erase fs find findstr format ftype graftabl help keyb label md mkdir mode more move path pause print popd pushd promt rd recover rem rename replace restore rmdir shiftsort start subst time title tree type ver verify vol ping net ipconfig taskkill xcopy ren del"
            )
        ),
        contains = listOf(
            Mode(
                className = "variable",

                begin =
                    """%%[^ ]|%[^ ]+?%|![^ ]+?!"""
            ),
            Mode(
                className = "function",

                begin = LABEL.begin,
                end = "goto:eof",

                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = "(listOf(_a-zA-Z]\\w*\\.)*(listOf(_a-zA-Z]\\w*:)?[_a-zA-Z]\\w*")),
                    COMMENT
                )
            ),
            Mode(
                className = "number",

                begin = "\\b\\d+",

                relevance = 0
            ),
            COMMENT
        )
    )
}
