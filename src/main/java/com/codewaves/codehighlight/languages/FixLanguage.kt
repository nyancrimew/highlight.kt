package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = FIX
Author = Brent Bradbury <brent@brentium.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class FixLanguage : LanguageBuilder {
    override fun build() = Mode(
        contains = listOf(
            Mode(
                begin =
                    """[^\u2401\u0001]+""",

                end =
                    """[\u2401\u0001]""",

                excludeEnd = true,
                returnBegin = true,
                returnEnd = false,
                contains = listOf(
                    Mode(
                        begin =
                            """(listOf(^\u2401\u0001=]+)""",

                        end =
                            """=(listOf(^\u2401\u0001=]+)""",

                        returnEnd = true,
                        returnBegin = false,
                        className = "attr"
                    ),
                    Mode(
                        begin =
                            """=""",

                        end =
                            """(listOf(\u2401\u0001))""",

                        excludeEnd = true,
                        excludeBegin = true,
                        className = "string"
                    )
                )
            )
        ),
        case_insensitive = true
    )
}
