package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Extended Backus-Naur Form
Author = Alex McKibben <alex@nullscope.net>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class EbnfLanguage : LanguageBuilder {
    val commentMode = hljs.COMMENT(
        """\(\*""",
        """\*\)"""
    )

    val nonTerminalMode = Mode(
        className = "attribute",

        begin =
            """^[ ]*[a-zA-Z][a-zA-Z-]*(listOf(\s-]+[a-zA-Z][a-zA-Z]*)*"""
    )

    val specialSequenceMode = Mode(
        className = "meta",

        begin =
            """\?.*\?"""
    )

    val ruleBodyMode = Mode(
        begin =
            """=""",

        end =
            """;""",

        contains = listOf(
            commentMode,
            specialSequenceMode,
            // terminals
            hljs.APOS_STRING_MODE, hljs.QUOTE_STRING_MODE
        )
    )

    override fun build() = Mode(
        illegal =
            """\S""",

        contains = listOf(
            commentMode,
            nonTerminalMode,
            ruleBodyMode
        )
    )
}
