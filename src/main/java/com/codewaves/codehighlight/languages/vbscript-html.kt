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
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/vbscript-html.js MD5 <4cc386b7eadeee4c7bb1afea12557f96>
 */
internal fun `vbscript-html`(): Mode {
    return Mode(
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
