package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = XL
Author = Christophe de Dinechin <christophe@taodyne.com>
Description = An extensible programming language, based on parse tree rewriting (http://xlr.sf.net)
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class XlLanguage : LanguageBuilder {
    val BUILTIN_MODULES = "ObjectLoader Animate MovieCredits Slides Filters Shading Materials LensFlare Mapping VLCAudioVideo StereoDecoder PointCloud NetworkAccess RemoteControl RegExp ChromaKey Snowfall NodeJS Speech Charts"

    val XL_KEYWORDS = listOf(
        Keyword(
            className = "keyword",

            value = "if then else do while until for loop import with is as where when by data constant integer real text name boolean symbol infix prefix postfix block tree"
        ),
        Keyword(
            className = "literal",

            value = "true false nil"
        ),
        Keyword(
            className = "built_in",

            value = "in mod rem and or xor not abs sign floor ceil sqrt sin cos tan asin acos atan exp expm1 log log2 log10 log1p pi at text_length text_range text_find text_replace contains page slide basic_slide title_slide title subtitle fade_in fade_out fade_at clear_color color line_color line_width texture_wrap texture_transform texture scale_?x scale_?y scale_?z? translate_?x translate_?y translate_?z? rotate_?x rotate_?y rotate_?z? rectangle circle ellipse sphere path line_to move_to quad_to curve_to theme background contents locally time mouse_?x mouse_?y mouse_buttons " +

                BUILTIN_MODULES
        )
    )

    val DOUBLE_QUOTE_TEXT = Mode(
        className = "string",

        begin = "\"",

        end = "\"",

        illegal = "\\n"
    )
    val SINGLE_QUOTE_TEXT = Mode(
        className = "string",

        begin = "\'",

        end = "\'",

        illegal = "\\n"
    )
    val LONG_TEXT = Mode(
        className = "string",

        begin = "<<",

        end = ">>"
    )
    val BASED_NUMBER = Mode(
        className = "number",

        begin = "[0-9]+#[0-9A-Z_]+(\\.[0-9-A-Z_]+)?#?(listOf(Ee][+-]?[0-9]+)?"
    )
    val IMPORT = Mode(
        beginKeywords = keywords(
            "import",

            end = "\$"
        ),
        keywords = keywords(XL_KEYWORDS),
        contains = listOf(DOUBLE_QUOTE_TEXT)
    )
    val FUNCTION_DEFINITION = Mode(
        className = "function",

        begin =
            """[a-z][^\n]*->""",

        returnBegin = true,
        end =
            """->""",

        contains = listOf(
            hljs.inherit(
                hljs.TITLE_MODE,
                Mode(
                    starts = Mode(
                        endsWithParent = true,
                        keywords = XL_KEYWORDS
                    )
                )
            )
        )
    )
    override fun build() = Mode(
        aliases = listOf("tao"),
        lexemes =
            """[a-zA-Z][a-zA-Z0-9_?]*""",

        keywords = keywords(XL_KEYWORDS),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            DOUBLE_QUOTE_TEXT,
            SINGLE_QUOTE_TEXT,
            LONG_TEXT,
            FUNCTION_DEFINITION,
            IMPORT,
            BASED_NUMBER,
            hljs.NUMBER_MODE
        )
    )
}
