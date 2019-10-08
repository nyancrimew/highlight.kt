package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = LDIF
Contributors = Jacob Childress <jacobc@gmail.com>
Category = enterprise, config
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LdifLanguage : LanguageBuilder {
    override fun build() = Mode(
        contains = listOf(
            Mode(
                className = "attribute",

                begin = "^dn",

                end = ": ",

                excludeEnd = true,
                starts = Mode(
                    end = "\$",

                    relevance = 0
                ),
                relevance = 10
            ),
            Mode(
                className = "attribute",

                begin = "^\\w",

                end = ": ",

                excludeEnd = true,
                starts = Mode(
                    end = "\$",

                    relevance = 0
                )
            ),
            Mode(
                className = "literal",

                begin = "^-",

                end = "\$"
            ),
            hljs.HASH_COMMENT_MODE
        )
    )
}
