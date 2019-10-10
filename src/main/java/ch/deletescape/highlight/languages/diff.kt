package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Diff
Description = Unified and context diff
Author = Vasily Polovnyov <vast@whiteants.net>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/diff.js MD5 <52c96b6ffaeebb6cc98f3bdf03c6cced>
 */
internal fun diff(): Mode {
    return Mode(
        aliases = listOf("patch"),
        contains = listOf(
            Mode(
                className = "meta",
                relevance = 10,
                variants = listOf(
                    Mode(begin =
                            """^@@ +\-\d+,\d+ +\+\d+,\d+ +@@${'$'}"""),
                    Mode(begin =
                            """^\*\*\* +\d+,\d+ +\*\*\*\*${'$'}"""),
                    Mode(begin =
                            """^\-\-\- +\d+,\d+ +\-\-\-\-${'$'}""")
                )
            ),
            Mode(
                className = "comment",
                variants = listOf(
                    Mode(
                        begin =
                            """Index = """,
                        end =
                            """${'$'}"""
                    ),
                    Mode(
                        begin =
                            """=Mode(3,}""",
                        end =
                            """${'$'}"""
                    ),
                    Mode(
                        begin =
                            """^\-{3}""",
                        end =
                            """${'$'}"""
                    ),
                    Mode(
                        begin =
                            """^\*{3} """,
                        end =
                            """${'$'}"""
                    ),
                    Mode(
                        begin =
                            """^\+{3}""",
                        end =
                            """${'$'}"""
                    ),
                    Mode(begin =
                            """^\*{15}${'$'}""")
                )
            ),
            Mode(
                className = "addition",
                begin = "^\\+",
                end = "\$"
            ),
            Mode(
                className = "deletion",
                begin = "^\\-",
                end = "\$"
            ),
            Mode(
                className = "addition",
                begin = "^\\!",
                end = "\$"
            )
        )
    )
}
