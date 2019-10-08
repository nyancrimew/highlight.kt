package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Smalltalk
Author = Vladimir Gubarkov <xonixx@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class SmalltalkLanguage : LanguageBuilder {
    val VAR_IDENT_RE = "[a-z][a-zA-Z0-9_]*"
    val CHAR = Mode(
        className = "string",

        begin = "\\$.{1}"
    )
    val SYMBOL = Mode(
        className = "symbol",

        begin = "#" +
            hljs.UNDERSCORE_IDENT_RE
    )
    override fun build() = Mode(
        aliases = listOf("st"),
        keywords = keywords("self super nil true false thisContext"), // only 6
        contains = listOf(
            hljs.COMMENT(
                """,
 """
            ),
            hljs.APOS_STRING_MODE,
            Mode(
                className = "type",

                begin = "\\b[A-Z][A-Za-z0-9_]*",

                relevance = 0
            ),
            Mode(
                begin = VAR_IDENT_RE + ":",

                relevance = 0
            ),
            hljs.C_NUMBER_MODE,
            SYMBOL,
            CHAR,
            Mode(
                // This looks more complicated than needed to avoid combinatorial
                // explosion under V8. It effectively means `| var1 var2 ... |` with
                // whitespace adjacent to `|` being optional.
                begin = "\\|[ ]*" +
                    VAR_IDENT_RE + "(listOf( ]+" +
                    VAR_IDENT_RE + ")*[ ]*\\|",

                returnBegin = true,
                end =
                    """\|""",

                illegal =
                    """\S""",

                contains = listOf(
                    Mode(
                        begin = "(\\|[ ]*)?" +
                            VAR_IDENT_RE
                    )
                )
            ),
            Mode(
                begin = "\\#\\(",

                end = "\\)",

                contains = listOf(
                    hljs.APOS_STRING_MODE,
                    CHAR,
                    hljs.C_NUMBER_MODE,
                    SYMBOL
                )
            )
        )
    )
}
