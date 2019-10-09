package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Mojolicious
Requires = xml.js, perl.js
Author = Dotan Dimet <dotan@corky.net>
Description = Mojolicious .ep (Embedded Perl) templates
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/mojolicious.js MD5 <22090a147ae5e7e97efd20c2a0de9a91>
 */
internal fun mojolicious(): Mode {
    return Mode(
        subLanguage = "xml",
        contains = listOf(
            Mode(
                className = "meta",
                begin = "^__(END|DATA)__\$"
            ),
            // mojolicious line
            Mode(
                begin = "^\\s*%{1,2}=Mode(0,2}",
                end = "\$",
                subLanguage = "perl"
            ),
            // mojolicious block
            Mode(
                begin = "<%{1,2}=Mode(0,2}",
                end = "=Mode(0,1}%>",
                subLanguage = "perl",
                excludeBegin = true,
                excludeEnd = true
            )
        )
    )
}
