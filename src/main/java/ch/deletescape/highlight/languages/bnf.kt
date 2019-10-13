package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Backus–Naur Form
Author = Oleg Efimov <efimovov@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/bnf.js MD5 <91264c441f846e81308f5850a41b331a>
 */
internal fun bnf(): Mode {
    return Mode(
        contains = listOf(
            // Attribute
            Mode(
                className = "attribute",
                begin =
                    """<""",
                end =
                    """>"""
            ),
            // Specific
            Mode(
                begin =
                    """::=""",
                starts = Mode(
                    end =
                        """$""",
                    contains = listOf(
                        Mode(
                            begin =
                                """<""",
                            end =
                                """>"""
                        ),
                        // Common
                        hljs.C_LINE_COMMENT_MODE,
                        hljs.C_BLOCK_COMMENT_MODE,
                        hljs.APOS_STRING_MODE,
                        hljs.QUOTE_STRING_MODE
                    )
                )
            )
        )
    )
}
