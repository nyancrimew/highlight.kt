package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.hljs
import com.codewaves.codehighlight.core.keywordsJson

/*
Language = Apache
Author = Ruslan Keba <rukeba@gmail.com>
Contributors = Ivan Sagalaev <maniac@softwaremaniacs.org>
Website = http:"""/rukeba.com"""
Description = language definition for Apache configuration files (httpd.conf & .htaccess)
Category = common, config
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ApacheLanguage : LanguageBuilder {
    val NUMBER = Mode(
        className = "number",
        begin = "[\\${'$'}%]\\d+"
    )

    override fun build() = Mode(
        aliases = listOf("apacheconf"),
        case_insensitive = true,
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            Mode(
                className = "section",
                begin = "</?",
                end = ">"
            ),
            Mode(
                className = "attribute",
                begin = """\w+""",
                relevance = 0,
                // keywords aren’t needed for highlighting per se, they only boost relevance
                // for a very generally defined mode (starts with a word, ends with line-end
                keywords = keywordsJson(
                    """
nomarkup = "order deny allow setenv rewriterule rewriteengine rewritecond documentroot sethandler errordocument loadmodule options header listen serverroot servername"""".trimIndent()
                ),
                starts = Mode(
                    end = """${'$'}""",
                    relevance = 0,
                    keywords = keywordsJson(
                        """
literal = "on off all"""".trimIndent()
                    ),
                    contains = listOf(
                        Mode(
                            className = "meta",
                            begin = "\\s\\[",
                            end = "\\]${'$'}"
                        ),
                        Mode(
                            className = "variable",
                            begin = "[\\${'$'}%]\\{",
                            end = "\\}",
                            contains = listOf(NUMBER)
                        ),
                        NUMBER,
                        hljs.QUOTE_STRING_MODE
                    )
                )
            )
        ),
        illegal = """\S"""
    )
}
