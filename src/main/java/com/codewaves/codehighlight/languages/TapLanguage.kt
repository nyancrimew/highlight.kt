package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Test Anything Protocol
Requires = yaml.js
Author = Sergey Bronnikov <sergeyb@bronevichok.ru>
Website = https://bronevichok.ru/
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class TapLanguage : LanguageBuilder {
    override fun build() = Mode(
        case_insensitive = true,
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            // version of format and total amount of testcases
            Mode(
                className = "meta",

                variants = listOf(
                    Mode(begin = "^TAP version (\\d+)\$"),
                    Mode(begin = "^1\\.\\.(\\d+)\$")
                )
            ),
            // YAML block
            Mode(
                begin = "(\s+)?---\$",

                end = "\\.\\.\\.\$",

                subLanguage = "yaml",

                relevance = 0
            ),
            // testcase number
            Mode(
                className = "number",

                begin = " (\\d+) "
            ),
            // testcase status and description
            Mode(
                className = "symbol",

                variants = listOf(
                    Mode(begin = "^ok"),
                    Mode(begin = "^not ok")
                )
            )
        )
    )
}
