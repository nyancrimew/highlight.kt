package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = TeX
Author = Vladimir Moskva <vladmos@gmail.com>
Website = http://fulc.ru/
Category = markup
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class TexLanguage : LanguageBuilder {
    val COMMAND = Mode(
        className = "tag",

        begin =
            """\\""",

        relevance = 0,
        contains = listOf(
            Mode(
                className = "name",

                variants = listOf(
                    Mode(begin =
                            """[a-zA-Z\u0430-\u044f\u0410-\u042f]+[*]?"""),
                    Mode(begin =
                            """[^a-zA-Z\u0430-\u044f\u0410-\u042f0-9]""")
                ),
                starts = Mode(
                    endsWithParent = true,
                    relevance = 0,
                    contains = listOf(
                        Mode(
                            className = "string",
                            // because it looks like attributes in HTML tags
                            variants = listOf(
                                Mode(
                                    begin =
                                        """\[""",

                                    end =
                                        """\]"""
                                ),
                                Mode(
                                    begin =
                                        """\{""",

                                    end =
                                        """\}"""
                                )
                            )
                        ),
                        Mode(
                            begin =
                                """\s*=\s*""",

                            endsWithParent = true,
                            relevance = 0,
                            contains = listOf(
                                Mode(
                                    className = "number",

                                    begin =
                                        """-?\d*\.?\d+(pt|pc|mm|cm|in|dd|cc|ex|em)?"""
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    override fun build() = Mode(
        contains = listOf(
            COMMAND,
            Mode(
                className = "formula",

                contains = listOf(COMMAND),
                relevance = 0,
                variants = listOf(
                    Mode(
                        begin =
                            """\${'$'}\${'$'}""",

                        end =
                            """\${'$'}\${'$'}"""
                    ),
                    Mode(
                        begin =
                            """\${'$'}""",

                        end =
                            """\${'$'}"""
                    )
                )
            ),
            hljs.COMMENT(
                "%",

                "\$",

                Mode(
                    relevance = 0
                )
            )
        )
    )
}
