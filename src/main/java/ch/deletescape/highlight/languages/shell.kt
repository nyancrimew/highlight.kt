package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Shell Session
Requires = bash.js
Author = TSUYUSATO Kitsune <make.just.on@gmail.com>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/shell.js MD5 <66f8311249a3150dbce80200aa9cc072>
 */
internal fun shell(): Mode {
    return Mode(
        aliases = listOf("console"),
        contains = listOf(
            Mode(
                className = "meta",
                begin = "^\\s{0,3}[\\w\\d\\[\\]()@-]*[>%\$#]",
                starts = Mode(
                    end = "\$",
                    subLanguage = "bash"
                )
            )
        )
    )
}
