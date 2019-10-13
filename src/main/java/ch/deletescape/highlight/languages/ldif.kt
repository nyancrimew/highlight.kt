package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = LDIF
Contributors = Jacob Childress <jacobc@gmail.com>
Category = enterprise, config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/ldif.js MD5 <5d1662a9390b1cf112d7ed1355d39a7c>
 */
internal fun ldif(): Mode {
    return Mode(
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
                end = "$"
            ),
            hljs.HASH_COMMENT_MODE
        )
    )
}
