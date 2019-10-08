package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Awk
Author = Matthew Daly <matthewbdaly@gmail.com>
Website = http:"""/matthewdaly.co.uk"""
Description = language definition for Awk scripts
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class AwkLanguage : LanguageBuilder {
    val VARIABLE = Mode(
        className = "variable",
        variants = listOf(
            Mode(
                begin =
                    """\${'$'}[\w\d#@][\w\d_]*"""
            ),
            Mode(
                begin =
                    """\${'$'}\{(.*?)}"""
            )
        )
    )
    val KEYWORDS = "BEGIN END if else while do for in break continue delete next nextfile function func exit|10"
    val STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE),
        variants = listOf(
            Mode(
                begin =
                    """(u|b)?r?'''""",
                end =
                    """'''""",
                relevance = 10
            ),
            Mode(
                begin =
                    """(u|b)?r?"""""",
                end =
                    """"""""",
                relevance = 10
            ),
            Mode(
                begin =
                    """(u|r|ur)'""",
                end =
                    """'""",
                relevance = 10
            ),
            Mode(
                begin =
                    """(u|r|ur)"""",
                end =
                    """"""",
                relevance = 10
            ),
            Mode(
                begin =
                    """(b|br)'""",
                end =
                    """'"""
            ),
            Mode(
                begin =
                    """(b|br)"""",
                end =
                    """""""
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE
        )
    )
    override fun build() = Mode(
        keywords = keywordsJson(
            """
            keyword = KEYWORDS
            """.trimIndent()
        ),
        contains = listOf(
            VARIABLE,
            STRING,
            hljs.REGEXP_MODE,
            hljs.HASH_COMMENT_MODE,
            hljs.NUMBER_MODE
        )
    )
}
