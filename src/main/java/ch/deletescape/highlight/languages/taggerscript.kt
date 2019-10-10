package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Tagger Script
Author = Philipp Wolfer <ph.wolfer@gmail.com>
Description = Syntax Highlighting for the Tagger Script as used by MusicBrainz Picard.
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/taggerscript.js MD5 <e2ff2ef5c2d4f56890e695e63941b1b6>
 */
internal fun taggerscript(): Mode {
    var COMMENT = Mode(
        className = "comment",
        begin =
            """${'$'}noop\(""",
        end =
            """\)""",
        contains = listOf(
            Mode(
                begin =
                    """\(""",
                end =
                    """\)""",
                contains = listOf(
                    hljs.SELF,
                    Mode(
                        begin =
                            """\\."""
                    )
                )
            )
        ),
        relevance = 10
    )
    var FUNCTION = Mode(
        className = "keyword",
        begin =
            """${'$'}(?!noop)[a-zA-Z][_a-zA-Z0-9]*""",
        end =
            """\(""",
        excludeEnd = true
    )
    var VARIABLE = Mode(
        className = "variable",
        begin =
            """%[_a-zA-Z0-9 = ]*""",
        end = "%"
    )
    var ESCAPE_SEQUENCE = Mode(
        className = "symbol",
        begin =
            """\\."""
    )
    return Mode(
        contains = listOf(
            COMMENT,
            FUNCTION,
            VARIABLE,
            ESCAPE_SEQUENCE
        )
    )
}
