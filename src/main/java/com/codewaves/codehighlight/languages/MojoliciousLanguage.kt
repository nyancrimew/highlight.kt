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
 * This class is automatically generated, avoid directly editing it if possible!
 */
class MojoliciousLanguage : LanguageBuilder {
    override fun build() = Mode(
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
