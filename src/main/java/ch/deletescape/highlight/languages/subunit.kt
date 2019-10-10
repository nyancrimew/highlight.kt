package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Mode

/*
Language = SubUnit
Author = Sergey Bronnikov <sergeyb@bronevichok.ru>
Website = https = //bronevichok.ru/
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/subunit.js MD5 <1b63b50b36c86db99202ad74d99bf17f>
 */
internal fun subunit(): Mode {
    var DETAILS = Mode(
        className = "string",
        begin = "\\[\n(multipart)?",
        end = "\\]\n"
    )
    var TIME = Mode(
        className = "string",
        begin = "\\d{4}-\\d{2}-\\d{2}(\\s+)\\d{2}:\\d{2}:\\d{2}\\.\\d+Z"
    )
    var PROGRESSVALUE = Mode(
        className = "string",
        begin = "(\\+|-)\\d+"
    )
    var KEYWORDS = Mode(
        className = "keyword",
        relevance = 10,
        variants = listOf(
            Mode(begin = "^(test|testing|success|successful|failure|error|skip|xfail|uxsuccess)(:?)\\s+(test)?"),
            Mode(begin = "^progress(:?)(\\s+)?(pop|push)?"),
            Mode(begin = "^tags = "),
            Mode(begin = "^time = ")

        )
    )
    return Mode(
        case_insensitive = true,
        contains = listOf(
            DETAILS,
            TIME,
            PROGRESSVALUE,
            KEYWORDS
        )
    )
}
