package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Golo
Author = Philippe Charriere <ph.charriere@gmail.com>
Description = a lightweight dynamic language for the JVM, see http = //golo-lang.org/
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/golo.js MD5 <ca0258e7f99ecf6f7729417b0e814f16>
 */
internal fun golo(): Mode {
    return Mode(
        keywords = listOf(
            keyword(
                className = "keyword",
                value =
                    "println readln print import module function local return let var while for foreach times in case when match with break continue augment augmentation each find filter reduce if then else otherwise try catch finally raise throw orIfNull DynamicObject|10 DynamicVariable struct Observable map set vector list array"
            ),
            keyword(
                className = "literal",
                value =
                    "true false null"
            )
        ).flatten(),
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
