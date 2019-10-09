package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = CSP
Description = Content Security Policy definition highlighting 
Author = Taras <oxdef@oxdef.info>
vim = ts=2 sw=2 st=2
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/csp.js MD5 <26babfc251e28f0c86915af8e6e123d5>
 */
internal fun csp(): Mode {
    return Mode(
        case_insensitive = false,
        lexemes = "[a-zA-Z][a-zA-Z0-9_-]*",
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "base-uri child-src connect-src default-src font-src form-action frame-ancestors frame-src img-src media-src object-src plugin-types report-uri sandbox script-src style-src"
            )
        ),
        contains = listOf(
            Mode(
                className = "string",
                begin = "'",
                end = "'"
            ),
            Mode(
                className = "attribute",
                begin = "^Content",
                end = ":",
                excludeEnd = true
            )
        )
    )
}
