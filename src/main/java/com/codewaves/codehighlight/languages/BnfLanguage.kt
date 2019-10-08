package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Backus–Naur Form
Author = Oleg Efimov <efimovov@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class BnfLanguage : LanguageBuilder {
    override fun build() = Mode(
        contains = listOf(
            // Attribute
            Mode(
                className = "attribute",

                begin =
                    """<""",

                end =
                    """>"""
            ),
            // Specific
            Mode(
                begin =
                    """::=""",

                starts = Mode(
                    end =
                        """${'$'}""",

                    contains = listOf(
                        Mode(
                            begin =
                                """<""",

                            end =
                                """>"""
                        ),
                        // Common
                        hljs.C_LINE_COMMENT_MODE,
                        hljs.C_BLOCK_COMMENT_MODE,
                        hljs.APOS_STRING_MODE,
                        hljs.QUOTE_STRING_MODE
                    )
                )
            )
        )
    )
}
