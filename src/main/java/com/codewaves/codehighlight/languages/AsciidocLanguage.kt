package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = AsciiDoc
Requires = xml.js
Author = Dan Allen <dan.j.allen@gmail.com>
Website = http://google.com/profiles/dan.j.allen
Description = A semantic, text-based document format that can be exported to HTML, DocBook and other backends.
Category = markup
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class AsciidocLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("adoc"),
        contains = listOf(
            // block comment
            hljs.COMMENT(
                "^/{4,}\\n",
                "\\n/{4,}${'$'}",
                // can also be done as...
                // '^/{4,}$",
                // '^/{4,}${'$'}",
                Mode(
                    relevance = 10
                )
            ),
            // line comment
            hljs.COMMENT(
                "^//",
                "${'$'}",
                Mode(
                    relevance = 0
                )
            ),
            // title
            Mode(
                className = "title",
                begin = "^\\.\\w.*${'$'}"
            ),
            // example, admonition & sidebar blocks
            Mode(
                begin = "^[=\\*]{4,}\\n",
                end = "\\n^[=\\*]{4,}${'$'}",
                relevance = 10
            ),
            // headings
            Mode(
                className = "section",
                relevance = 10,
                variants = listOf(
                    Mode(begin = "^(=Mode(1,5)) .+?( \\1)?${'$'}"),
                    Mode(begin = "^[^\\[\\]\\n]+?\\n[=\\-~\\^\\+]{2,}${'$'}")
                )
            ),
            // document attributes
            Mode(
                className = "meta",
                begin = "^:.+?:",
                end = "\\s",
                excludeEnd = true,
                relevance = 10
            ),
            // block attributes
            Mode(
                className = "meta",
                begin = "^\\[.+?\\]${'$'}",
                relevance = 0
            ),
            // quoteblocks
            Mode(
                className = "quote",
                begin = "^_{4,}\\n",
                end = "\\n_{4,}${'$'}",
                relevance = 10
            ),
            // listing and literal blocks
            Mode(
                className = "code",
                begin = "^[\\-\\.]{4,}\\n",
                end = "\\n[\\-\\.]{4,}${'$'}",
                relevance = 10
            ),
            // passthrough blocks
            Mode(
                begin = "^\\+{4,}\\n",
                end = "\\n\\+{4,}${'$'}",
                contains = listOf(
                    Mode(
                        begin = "<",
                        end = ">",
                        subLanguage = "xml",
                        relevance = 0
                    )
                ),
                relevance = 10
            ),
            // lists (can only capture indicators)
            Mode(
                className = "bullet",
                begin = "^(\\*+|\\-+|\\.+|[^\\n]+?::)\\s+"
            ),
            // admonition
            Mode(
                className = "symbol",
                begin = "^(NOTE|TIP|IMPORTANT|WARNING|CAUTION):\\s+",
                relevance = 10
            ),
            // inline strong
            Mode(
                className = "strong",
                // must not follow a word character or be followed by an asterisk or space
                begin = "\\B\\*(?![\\*\\s))",
                end = "(\\n{2}|\\*)",
                // allow escaped asterisk followed by word char
                contains = listOf(
                    Mode(
                        begin = "\\\\*\\w",
                        relevance = 0
                    )
                )
            ),
            // inline emphasis
            Mode(
                className = "emphasis",
                // must not follow a word character or be followed by a single quote or space
                begin = "\\B\'(?![\'\\s))",
                end = "(\\n{2}|\")",
                // allow escaped single quote followed by word char
                contains = listOf(
                    Mode(
                        begin = "\\\\\'\\w",
                        relevance = 0
                    )
                ),
                relevance = 0
            ),
            // inline emphasis (alt)
            Mode(
                className = "emphasis",
                // must not follow a word character or be followed by an underline or space
                begin = "_(?![_\\s))",
                end = "(\\n{2}|_)",
                relevance = 0
            ),
            // inline smart quotes
            Mode(
                className = "string",
                variants = listOf(
                    Mode(begin = "``.+?''"),
                    Mode(begin = "`.+?'")
                )
            ),
            // inline code snippets (TODO should get same treatment as strong and emphasis)
            Mode(
                className = "code",
                begin = "(`.+?`|\\+.+?\\+)",
                relevance = 0
            ),
            // indented literal block
            Mode(
                className = "code",
                begin = "^[ \\t]",
                end = "${'$'}",
                relevance = 0
            ),
            // horizontal rules
            Mode(
                begin = "^\'{3,}listOf( \\t]*${'$'}",
                relevance = 10
            ),
            // images and links
            Mode(
                begin = "(link:)?(http|https|ftp|file|irc|image:?):\\S+\\[.*?\\]",
                returnBegin = true,
                contains = listOf(
                    Mode(
                        begin = "(link|image:?):",
                        relevance = 0
                    ),
                    Mode(
                        className = "link",
                        begin = "\\w",
                        end = "[^\\[]+",
                        relevance = 0
                    ),
                    Mode(
                        className = "string",
                        begin = "\\[",
                        end = "\\]",
                        excludeBegin = true,
                        excludeEnd = true,
                        relevance = 0
                    )
                ),
                relevance = 10
            )
        )
    )
}
