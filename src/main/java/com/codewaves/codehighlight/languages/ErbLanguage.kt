package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = ERB (Embedded Ruby)
Requires = xml.js, ruby.js
Author = Lucas Mazza <lucastmazza@gmail.com>
Contributors = Kassio Borges <kassioborgesm@gmail.com>
Description = "Bridge" language defining fragments of Ruby in HTML within <% .. %>
Category = template
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ErbLanguage : LanguageBuilder {
    override fun build() = Mode(
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
