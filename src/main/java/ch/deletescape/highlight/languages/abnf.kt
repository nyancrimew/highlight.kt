package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keywords

/*
Language = Augmented Backus-Naur Form
Author = Alex McKibben <alex@nullscope.net>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/abnf.js MD5 <4a74d5edda09e836fb627b7fdc75baf9>
 */
internal fun abnf(): Mode {
    var keywords = keywords(
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
    var commentMode = hljs.COMMENT(
        ";",
        "$"
    )
    var terminalBinaryMode = Mode(
        className = "symbol",
        begin =
            """%b[0-1]+(-[0-1]+|(\.[0-1]+)+){0,1}"""
    )
    var terminalDecimalMode = Mode(
        className = "symbol",
        begin =
            """%d[0-9]+(-[0-9]+|(\.[0-9]+)+){0,1}"""
    )
    var terminalHexadecimalMode = Mode(
        className = "symbol",
        begin =
            """%x[0-9A-F]+(-[0-9A-F]+|(\.[0-9A-F]+)+){0,1}"""
    )
    var caseSensitivityIndicatorMode = Mode(
        className = "symbol",
        begin =
            """%[si]"""
    )
    var ruleDeclarationMode = Mode(
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
    return Mode(
        illegal = regexes.unexpectedChars,
        keywords = keywords(keywords.joinToString(" ")),
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

object regexes {
    const val ruleDeclaration = "^[a-zA-Z][a-zA-Z0-9-]*"
    const val unexpectedChars = "[!@#\$^&\",?+~`|:]"
}
