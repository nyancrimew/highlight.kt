package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Golo
Author = Philippe Charriere <ph.charriere@gmail.com>
Description = a lightweight dynamic language for the JVM, see http://golo-lang.org/
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class GoloLanguage : LanguageBuilder {
    override fun build() = Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "println readln print import module function local return let val while for foreach times in case when match with break continue augment augmentation each find filter reduce if then else otherwise try catch finally raise throw orIfNull DynamicObject|10 DynamicVariable struct Observable map set vector list array"
            ),
            Keyword(
                className = "literal",

                value = "true false null"
            )
        ),
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",

                begin = "@[A-Za-z]+"
            )
        )
    )
}
