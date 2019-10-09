package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Protocol Buffers
Author = Dan Tao <daniel.tao@gmail.com>
Description = Protocol buffer message definition format
Category = protocols
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/protobuf.js MD5 <ea9135245d247cd55bed845ee6c919eb>
 */
internal fun protobuf(): Mode {
    return Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "package import option optional required repeated group oneof"
            ),
            Keyword(
                className = "built_in",
                value = "double float int32 int64 uint32 uint64 sint32 sint64 fixed32 fixed64 sfixed32 sfixed64 bool string bytes"
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
            Mode(
                className = "class",
                beginKeywords = keywords("message enum service"),
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
                className = "function",
                beginKeywords = keywords("rpc"),
                end =
                    """;""",
                excludeEnd = true,
                keywords = keywords("rpc returns")
            ),
            Mode(
                begin =
                    """^\s*[A-Z_]+""",
                end =
                    """\s*=""",
                excludeEnd = true
            )
        )
    )
}
