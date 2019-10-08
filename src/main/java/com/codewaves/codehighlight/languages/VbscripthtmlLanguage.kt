package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = VBScript in HTML
Requires = xml.js, vbscript.js
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Description = "Bridge" language defining fragments of VBScript in HTML within <% .. %>
Category = scripting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class VbscripthtmlLanguage : LanguageBuilder {
    override fun build() = Mode(
        subLanguage = "xml",

        contains = listOf(
            Mode(
                begin = "<%",

                end = "%>",

                subLanguage = "vbscript"
            )
        )
    )
}
