package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Extended Backus-Naur Form
Author = Alex McKibben <alex@nullscope.net>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/ebnf.js MD5 <69f89941064ae271ced4af9d37e4ce3f>
 */
internal fun ebnf(): Mode {
    var commentMode = hljs.COMMENT("""\(\*""", """\*\)""")
    var nonTerminalMode = Mode(
        className = "attribute",
        begin =
            """^[ ]*[a-zA-Z][a-zA-Z-]*([\s-]+[a-zA-Z][a-zA-Z]*)*"""
    )
    var specialSequenceMode = Mode(
        className = "meta",
        begin =
            """\?.*\?"""
    )
    var ruleBodyMode = Mode(
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
    return Mode(
        illegal =
            """\S""",
        contains = listOf(
            commentMode,
            nonTerminalMode,
            ruleBodyMode
        )
    )
}
