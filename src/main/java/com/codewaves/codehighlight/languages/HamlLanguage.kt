package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Haml
Requires = ruby.js
Author = Dan Allen <dan.j.allen@gmail.com>
Website = http://google.com/profiles/dan.j.allen
Category = template
*/

// TODO support filter tags like :javascript, support inline HTML
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class HamlLanguage : LanguageBuilder {
    override fun build() = Mode(
        case_insensitive = true,
        contains = listOf(
            Mode(
                className = "meta",

                begin = "^!!!( (5|1\\.1|Strict|Frameset|Basic|Mobile|RDFa|XML\\b.*))?\$",

                relevance = 10
            ),
            // FIXME these comments should be allowed to span indented lines
            hljs.COMMENT(
                "^\\s*(!=#|=#|-#|/).*\$",

                false,
                Mode(
                    relevance = 0
                )
            ),
            Mode(
                begin = "^\\s*(-|=|!=)(?!#)",

                starts = Mode(
                    end = "\\n",

                    subLanguage = "ruby"
                )
            ),
            Mode(
                className = "tag",

                begin = "^\\s*%",

                contains = listOf(
                    Mode(
                        className = "selector-tag",

                        begin = "\\w+"
                    ),
                    Mode(
                        className = "selector-id",

                        begin = "#[\\w-]+"
                    ),
                    Mode(
                        className = "selector-class",

                        begin = "\\.[\\w-]+"
                    ),
                    Mode(
                        begin = "{\\s*",

                        end = "\\s*}",

                        contains = listOf(
                            Mode(
                                begin = ":\\w+\\s*=>",

                                end = ",\\s+",

                                returnBegin = true,
                                endsWithParent = true,
                                contains = listOf(
                                    Mode(
                                        className = "attr",

                                        begin = ":\\w+"
                                    ),
                                    hljs.APOS_STRING_MODE,
                                    hljs.QUOTE_STRING_MODE,
                                    Mode(
                                        begin = "\\w+",

                                        relevance = 0
                                    )
                                )
                            )
                        )
                    ),
                    Mode(
                        begin = "\\(\\s*",

                        end = "\\s*\\)",

                        excludeEnd = true,
                        contains = listOf(
                            Mode(
                                begin = "\\w+\\s*=",

                                end = "\\s+",

                                returnBegin = true,
                                endsWithParent = true,
                                contains = listOf(
                                    Mode(
                                        className = "attr",

                                        begin = "\\w+",

                                        relevance = 0
                                    ),
                                    hljs.APOS_STRING_MODE,
                                    hljs.QUOTE_STRING_MODE,
                                    Mode(
                                        begin = "\\w+",

                                        relevance = 0
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            Mode(
                begin = "^\\s*[=~]\\s*"
            ),
            Mode(
                begin = "#{",

                starts = Mode(
                    end = "}",

                    subLanguage = "ruby"
                )
            )
        )
    )
}
