package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = FIX
Author = Brent Bradbury <brent@brentium.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/fix.js MD5 <e77d402c890046a02e43a5ad14df6e71>
 */
internal fun fix(): Mode {
    return Mode(
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
                            """([^\u2401\u0001=]+)""",
                        end =
                            """=([^\u2401\u0001=]+)""",
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
