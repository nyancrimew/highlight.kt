package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Brainfuck
Author = Evgeny Stepanischev <imbolk@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/brainfuck.js MD5 <e07b96c0792b9b6539237bed8f84ee01>
 */
internal fun brainfuck(): Mode {
    var LITERAL = Mode(
        className = "literal",
        begin = "[\\+\\-]",
        relevance = 0
    )
    return Mode(
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
