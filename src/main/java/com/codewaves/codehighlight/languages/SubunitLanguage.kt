package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = SubUnit
Author = Sergey Bronnikov <sergeyb@bronevichok.ru>
Website = https://bronevichok.ru/
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class SubunitLanguage : LanguageBuilder {
    val DETAILS = Mode(
        className = "string",

        begin = "\\[\n(multipart)?",

        end = "\\]\n"
    )
    val TIME = Mode(
        className = "string",

        begin = "\\d{4}-\\d{2}-\\d{2}(\\s+)\\d{2}:\\d{2}:\\d{2}\.\\d+Z"
    )
    val PROGRESSVALUE = Mode(
        className = "string",

        begin = "(\\+|-)\\d+"
    )
    val KEYWORDS = listOf(
        Keyword(
            className = "className",

            value = "keyword"
        ),
        Keyword(
            className = "relevance",

            value = 10
        ),
        Keyword(
            className = "variants",

            value = listOf(
                Mode(begin = "^(test|testing|success|successful|failure|error|skip|xfail|uxsuccess)(:?)\\s+(test)?")
            ),
            Mode(begin = "^progress(:?)(\\s+)?(pop|push)?"),
            Mode(begin = "^tags:"),
            Mode(begin = "^time:")
        )
    )
    override fun build() = Mode(
        case_insensitive = true,
        contains = listOf(
            DETAILS,
            TIME,
            PROGRESSVALUE,
            KEYWORDS
        )
    )
}
