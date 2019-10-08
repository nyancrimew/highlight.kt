package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.hljs
import com.codewaves.codehighlight.core.keywords

/*
Language = Augmented Backus-Naur Form
Author = Alex McKibben <alex@nullscope.net>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class AbnfLanguage : LanguageBuilder {
    object regexes {
        val ruleDeclaration = "^[a-zA-Z][a-zA-Z0-9-]*"
        val unexpectedChars = "[!@#${'$'}^&\",?+~`|:]"
    }

    val KEYWORDS = listOf(
        "ALPHA",
        "BIT",
        "CHAR",
        "CR",
        "CRLF",
        "CTL",
        "DIGIT",
        "DQUOTE",
        "HEXDIG",
        "HTAB",
        "LF",
        "LWSP",
        "OCTET",
        "SP",
        "VCHAR",
        "WSP"
    )

    val commentMode = hljs.COMMENT(";", "${'$'}")

    val terminalBinaryMode = Mode(
        className = "symbol",
        begin =
            """%b[0-1]+(-[0-1]+|(\.[0-1]+)+){0,1}"""
    )

    val terminalDecimalMode = Mode(
        className = "symbol",
        begin =
            """%d[0-9]+(-[0-9]+|(\.[0-9]+)+){0,1}"""
    )

    val terminalHexadecimalMode = Mode(
        className = "symbol",
        begin =
            """%x[0-9A-F]+(-[0-9A-F]+|(\.[0-9A-F]+)+){0,1}"""
    )

    val caseSensitivityIndicatorMode = Mode(
        className = "symbol",
        begin =
            """%[si]"""
    )

    val ruleDeclarationMode = Mode(
        begin = regexes.ruleDeclaration + "\\s*=",
        returnBegin = true,
        end =
            """=""",
        relevance = 0,
        contains = listOf(
            Mode(
                className = "attribute",
                begin = regexes.ruleDeclaration
            )
        )
    )

    override fun build() = Mode(
        illegal = regexes.unexpectedChars,
        keywords = keywords(KEYWORDS.joinToString(" ")),
        contains = listOf(
            ruleDeclarationMode,
            commentMode,
            terminalBinaryMode,
            terminalDecimalMode,
            terminalHexadecimalMode,
            caseSensitivityIndicatorMode,
            hljs.QUOTE_STRING_MODE,
            hljs.NUMBER_MODE
        )
    )
}
