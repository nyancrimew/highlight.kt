package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Shell Session
Requires = bash.js
Author = TSUYUSATO Kitsune <make.just.on@gmail.com>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ShellLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("console"),
        contains = listOf(
            Mode(
                className = "meta",

                begin = "^\\s{0,3}listOf(\\w\\d\\[\\]()@-]*[>%\$#]",

                starts = Mode(
                    end = "\$",

                    subLanguage = "bash"
                )
            )
        )
    )
}
