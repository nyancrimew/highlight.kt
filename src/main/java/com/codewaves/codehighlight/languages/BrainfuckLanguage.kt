package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Brainfuck
Author = Evgeny Stepanischev <imbolk@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class BrainfuckLanguage : LanguageBuilder {
    val LITERAL = Mode(
        className = "literal",

        begin = "[\\+\\-]",

        relevance = 0
    )
    override fun build() = Mode(
        aliases = listOf("bf"),
        contains = listOf(
            hljs.COMMENT(
                "[^\\[\\]\\.,\\+\\-<> \r\n]",

                "[\\[\\]\\.,\\+\\-<> \r\n]",

                Mode(
                    returnEnd = true,
                    relevance = 0
                )
            ),
            Mode(
                className = "title",

                begin = "[\\[\\]]",

                relevance = 0
            ),
            Mode(
                className = "string",

                begin = "[\\.,]",

                relevance = 0
            ),
            Mode(
                // this mode works as the only relevance counter
                begin =
                    """\+\+|\-\-""",

                returnBegin = true,
                contains = listOf(LITERAL)
            ),
            LITERAL
        )
    )
}
