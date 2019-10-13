package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = R
Author = Joe Cheng <joe@rstudio.org>
Category = scientific
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/r.js MD5 <ca8be3e0974100a2fb7b1aa69829a548>
 */
internal fun r(): Mode {
    var IDENT_RE = "([a-zA-Z]|\\.[a-zA-Z.])[a-zA-Z0-9._]*"
    return Mode(
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            Mode(
                begin = IDENT_RE,
                lexemes = IDENT_RE,
                keywords = listOf(
                    keyword(
                        className = "keyword",
                        value =
                            "function if in break next repeat else for return switch while try tryCatch stop warning require library attach detach source setMethod setGeneric setGroupGeneric setClass ..."
                    ),
                    keyword(
                        className = "literal",
                        value =
                            "NULL NA TRUE FALSE T F Inf NaN NA_integer_|10 NA_real_|10 NA_character_|10 NA_complex_|10"
                    )
                ).flatten(),
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
