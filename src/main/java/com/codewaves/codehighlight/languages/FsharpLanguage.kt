package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = F#
Author = Jonas Follesø <jonas@follesoe.no>
Contributors = Troy Kershaw <hello@troykershaw.com>, Henrik Feldt <henrik@haf.se>
Category = functional
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class FsharpLanguage : LanguageBuilder {
    val TYPEPARAM = Mode(
        begin = "<",

        end = ">",

        contains = listOf(
            hljs.inherit(hljs.TITLE_MODE, Mode(begin =
                    """'[a-zA-Z0-9_]+"""))
        )
    )

    override fun build() = Mode(
        aliases = listOf("fs"),
        keywords = keywords("abstract and as assert base begin class default delegate do done downcast downto elif else end exception extern false finally for fun function global if in inherit inline interface internal lazy let match member module mutable namespace new null of open or override private public rec return sig static struct then to true try type upcast use val void when while with yield"),
        illegal =
            """\/\*""",

        contains = listOf(
            Mode(
                // monad builder keywords (matches before non-bang kws)
                className = "keyword",

                begin =
                    """\b(yield|return|let|do)!"""
            ),
            Mode(
                className = "string",

                begin = "@\"",

                end = "\"",

                contains = listOf(Mode(begin = "\"\""))
            ),
            Mode(
                className = "string",

                begin = "\"\"\"",

                end = "\"\"\""
            ),
            hljs.COMMENT(
                "\\(\\*",
                "\\*\\)"
            ),
            Mode(
                className = "class",

                beginKeywords = keywords(
                    "type",

                    end = "\\(|=|\$",

                    excludeEnd = true
                ),
                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE,
                    TYPEPARAM
                )
            ),
            Mode(
                className = "meta",

                begin = "\\[<",

                end = ">\\]",

                relevance = 10
            ),
            Mode(
                className = "symbol",

                begin = "\\B(\'[A-Za-z))\\b",

                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
            hljs.C_NUMBER_MODE
        )
    )
}
