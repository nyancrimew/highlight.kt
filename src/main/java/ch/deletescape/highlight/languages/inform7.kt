package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Inform 7
Author = Bruno Dias <bruno.r.dias@gmail.com>
Description = Language definition for Inform 7, a DSL for writing parser interactive fiction.
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/inform7.js MD5 <82b35bb5a6b217c97275ab474e7169ef>
 */
internal fun inform7(): Mode {
    var START_BRACKET = "\\["
    var END_BRACKET = "\\]"
    return Mode(
        aliases = listOf("i7"),
        case_insensitive = true,
        keywords = listOf(
            // Some keywords more or less unique to I7, for relevance.
            Keyword(
                className = "keyword",
                value =
                    // kind:
                    "thing room person man woman animal container supporter backdrop door scenery open closed locked inside gender is are say understand kind of rule"
            )
        ),
        contains = listOf(
            Mode(
                className = "string",
                begin = "\"",
                end = "\"",
                relevance = 0,
                contains = listOf(
                    Mode(
                        className = "subst",
                        begin = START_BRACKET,
                        end = END_BRACKET
                    )
                )
            ),
            Mode(
                className = "section",
                begin =
                    """^(Volume|Book|Part|Chapter|Section|Table)\b""",
                end = "\${'$'}"
            ),
            Mode(
                // Rule definition
                // This is here for relevance.
                begin =
                    """^(Check|Carry out|Report|Instead of|To|Rule|When|Before|After)\b""",
                end = ":",
                contains = listOf(
                    Mode(
                        // Rule name
                        begin = "\\(This",
                        end = "\\)"
                    )
                )
            ),
            Mode(
                className = "comment",
                begin = START_BRACKET,
                end = END_BRACKET,
                contains = listOf(hljs.SELF)
            )
        )
    )
}
