package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Python profile
Description = Python profiler results
Author = Brian Beck <exogen@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ProfileLanguage : LanguageBuilder {
    override fun build() = Mode(
        contains = listOf(
            hljs.C_NUMBER_MODE,
            Mode(
                begin = "[a-zA-Z_][\\da-zA-Z_]+\\.[\\da-zA-Z_]{1,3}",

                end = ":",

                excludeEnd = true
            ),
            Mode(
                begin = "(ncalls|tottime|cumtime)",

                end = "\$",

                keywords = keywords("ncalls tottime|10 cumtime|10 filename"),
                relevance = 10
            ),
            Mode(
                begin = "function calls",

                end = "\$",

                contains = listOf(hljs.C_NUMBER_MODE),
                relevance = 10
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "string",

                begin = "\\(",

                end = "\\)\$",

                excludeBegin = true,
                excludeEnd = true,
                relevance = 0
            )
        )
    )
}
