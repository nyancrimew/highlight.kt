package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Thrift
Author = Oleg Efimov <efimovov@gmail.com>
Description = Thrift message definition format
Category = protocols
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/thrift.js MD5 <cf442248bae6fc677be0da5687b8c3b7>
 */
internal fun thrift(): Mode {
    var BUILT_IN_TYPES = "bool byte i16 i32 i64 double string binary"
    return Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "namespace const typedef struct enum service exception void oneway set list map required optional"
            ),
            Keyword(
                className = "built_in",
                value = BUILT_IN_TYPES
            ),
            Keyword(
                className = "literal",
                value = "true false"
            )
        ),
        contains = listOf(
            hljs.QUOTE_STRING_MODE,
            hljs.NUMBER_MODE,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "class",
                beginKeywords = keywords("struct enum service exception"),
                end =
                    """\{""",
                illegal =
                    """\n""",
                contains = listOf(
                    hljs.inherit(
                        hljs.TITLE_MODE,
                        Mode(
                            starts = Mode(
                                endsWithParent = true,
                                excludeEnd = true
                            ) // hack: eating everything after the first title
                        )
                    )
                )
            ),
            Mode(
                begin = "\\b(set|list|map)\\s*<",
                end = ">",
                keywords = keywords(BUILT_IN_TYPES),
                contains = listOf(hljs.SELF)
            )
        )
    )
}
