package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.keywordsJson

/*
Language = CSP
Description = Content Security Policy definition highlighting 
Author = Taras <oxdef@oxdef.info>

vim = ts=2 sw=2 st=2
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CspLanguage : LanguageBuilder {
    override fun build() = Mode(
        case_insensitive = false,
        lexemes = "[a-zA-Z][a-zA-Z0-9_-]*",
        keywords = keywordsJson(
            """
keyword = "base-uri child-src connect-src default-src font-src form-action frame-ancestors frame-src img-src media-src object-src plugin-types report-uri sandbox script-src style-src", """.trimIndent()
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
