package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Tagger Script
Author = Philipp Wolfer <ph.wolfer@gmail.com>
Description = Syntax Highlighting for the Tagger Script as used by MusicBrainz Picard.
 */
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class TaggerscriptLanguage : LanguageBuilder {

    val COMMENT = Mode(
        className = "comment",

        begin =
            """\${'$'}noop\(""",

        end =
            """\)""",

        contains = listOf(
            Mode(
                begin =
                    """\(""",

                end =
                    """\)""",

                contains = listOf(
                    Mode(
                        begin =
                            """\\."""
                    )
                )
            )
        ),
        relevance = 10
    )

    val FUNCTION = Mode(
        className = "keyword",

        begin =
            """\${'$'}(?!noop)[a-zA-Z][_a-zA-Z0-9]*""",

        end =
            """\(""",

        excludeEnd = true
    )

    val VARIABLE = Mode(
        className = "variable",

        begin =
            """%[_a-zA-Z0-9:]*""",

        end = "%"
    )

    val ESCAPE_SEQUENCE = Mode(
        className = "symbol",

        begin =
            """\\."""
    )

    override fun build() = Mode(
        contains = listOf(
            COMMENT,
            FUNCTION,
            VARIABLE,
            ESCAPE_SEQUENCE
        )
    )
}
