package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Diff
Description = Unified and context diff
Author = Vasily Polovnyov <vast@whiteants.net>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DiffLanguage : LanguageBuilder {
    override fun build() = Mode(
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
