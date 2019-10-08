package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = R
Author = Joe Cheng <joe@rstudio.org>
Category = scientific
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class RLanguage : LanguageBuilder {
    val IDENT_RE = "(listOf(a-zA-Z]|\\.[a-zA-Z.))[a-zA-Z0-9._]*"

    override fun build() = Mode(
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            Mode(
                begin = IDENT_RE,
                lexemes = IDENT_RE,
                keywords = listOf(
                    Keyword(
                        className = "keyword",

                        value = "function if in break next repeat else for return switch while try tryCatch stop warning require library attach detach source setMethod setGeneric setGroupGeneric setClass ..."
                    ),
                    Keyword(
                        className = "literal",

                        value = "NULL NA TRUE FALSE T F Inf NaN NA_integer_|10 NA_real_|10 NA_character_|10 NA_complex_|10"
                    )
                ),
                relevance = 0
            ),
            Mode(
                // hex value
                className = "number",

                begin = "0[xX][0-9a-fA-F]+[Li]?\\b",

                relevance = 0
            ),
            Mode(
                // explicit integer
                className = "number",

                begin = "\\d+(?:[eE][+\\-]?\\d*)?L\\b",

                relevance = 0
            ),
            Mode(
                // number with trailing decimal
                className = "number",

                begin = "\\d+\\.(?!\\d)(?:i\\b)?",

                relevance = 0
            ),
            Mode(
                // number
                className = "number",

                begin = "\\d+(?:\\.\\d*)?(?:[eE][+\\-]?\\d*)?i?\\b",

                relevance = 0
            ),
            Mode(
                // number with leading decimal
                className = "number",

                begin = "\\.\\d+(?:[eE][+\\-]?\\d*)?i?\\b",

                relevance = 0
            ),

            Mode(
                // escaped identifier
                begin = "`",

                end = "`",

                relevance = 0
            ),

            Mode(
                className = "string",

                contains = listOf(hljs.BACKSLASH_ESCAPE),
                variants = listOf(
                    Mode(
                        begin = "\"",

                        end = "\""
                    ),
                    Mode(
                        begin = "'",

                        end = "'"
                    )
                )
            )
        )
    )
}
