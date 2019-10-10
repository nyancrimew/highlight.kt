package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Test Anything Protocol
Requires = yaml.js
Author = Sergey Bronnikov <sergeyb@bronevichok.ru>
Website = https = //bronevichok.ru/
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/tap.js MD5 <0ad4073804c77912c5c92f3c24dbd863>
 */
internal fun tap(): Mode {
    return Mode(
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
                begin = "(\\s+)?---\$",
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
