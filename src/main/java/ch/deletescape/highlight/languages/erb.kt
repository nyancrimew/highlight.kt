package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = ERB (Embedded Ruby)
Requires = xml.js, ruby.js
Author = Lucas Mazza <lucastmazza@gmail.com>
Contributors = Kassio Borges <kassioborgesm@gmail.com>
Description = "Bridge" language defining fragments of Ruby in HTML within <% .. %>
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/erb.js MD5 <0edf76491fc264cbe5fa0c5c54cf7258>
 */
internal fun erb(): Mode {
    return Mode(
        subLanguage = "xml",
        contains = listOf(
            hljs.COMMENT(
                "<%#",
                "%>"
            ),
            Mode(
                begin = "<%[%=-]?",
                end = "[%-]?%>",
                subLanguage = "ruby",
                excludeBegin = true,
                excludeEnd = true
            )
        )
    )
}
