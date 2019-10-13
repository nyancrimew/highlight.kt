package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = HTTP
Description = HTTP request and response headers with automatic body highlighting
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Category = common, protocols
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/http.js MD5 <4299339f060ed2fd48a7d9bfb3b71a29>
 */
internal fun http(): Mode {
    var VERSION = "HTTP/[0-9\\.]+"
    return Mode(
        aliases = listOf("https"),
        illegal = "\\S",
        contains = listOf(
            Mode(
                begin = "^" +
                    VERSION,
                end = "$",
                contains = listOf(
                    Mode(
                        className = "number",
                        begin = "\\b\\d{3}\\b"
                    )
                )
            ),
            Mode(
                begin = "^[A-Z]+ (.*?) " +
                    VERSION + "\$",
                returnBegin = true,
                end = "$",
                contains = listOf(
                    Mode(
                        className = "string",
                        begin = " ",
                        end = " ",
                        excludeBegin = true,
                        excludeEnd = true
                    ),
                    Mode(
                        begin = VERSION
                    ),
                    Mode(
                        className = "keyword",
                        begin = "[A-Z]+"
                    )
                )
            ),
            Mode(
                className = "attribute",
                begin = "^\\w",
                end = ": ",
                excludeEnd = true,
                illegal = "\\n|\\s|=",
                starts = Mode(
                    end = "$",
                    relevance = 0
                )
            ),
            Mode(
                begin = "\\n\\n",
                starts = Mode(
                    subLanguages = listOf(),
                    endsWithParent = true
                )
            )
        )
    )
}
