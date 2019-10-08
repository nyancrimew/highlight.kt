package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Cap’n Proto
Author = Oleg Efimov <efimovov@gmail.com>
Description = Cap’n Proto message definition format
Category = protocols
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CapnprotoLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("capnp"),
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "struct enum interface union group import using const annotation extends in of on as with from fixed"
            ),
            Keyword(
                className = "built_in",

                value = "Void Bool Int8 Int16 Int32 Int64 UInt8 UInt16 UInt32 UInt64 Float32 Float64 Text Data AnyPointer AnyStruct Capability List"
            ),
            Keyword(
                className = "literal",

                value = "true false"
            )
        ),
        contains = listOf(
            hljs.QUOTE_STRING_MODE,
            hljs.NUMBER_MODE,
            hljs.HASH_COMMENT_MODE,
            Mode(
                className = "meta",

                begin =
                    """@0x[\w\d]{16);""",

                illegal =
                    """\n"""
            ),
            Mode(
                className = "symbol",

                begin =
                    """@\d+\b"""
            ),
            Mode(
                className = "class",

                beginKeywords = keywords(
                    "struct enum",

                    end =
                        """\{"""
                ),
                illegal =
                    """\n""",

                contains = listOf(
                    hljs.inherit(
                        hljs.TITLE_MODE,
                        Mode(
                            starts = Mode(
                                endsWithParent = true,
                                excludeEnd = true
                            ) // hack = eating everything after the first title
                        )
                    )
                )
            ),
            Mode(
                className = "class",

                beginKeywords = keywords(
                    "interface",

                    end =
                        """\{"""
                ),
                illegal =
                    """\n""",

                contains = listOf(
                    hljs.inherit(
                        hljs.TITLE_MODE,
                        Mode(
                            starts = Mode(
                                endsWithParent = true,
                                excludeEnd = true
                            ) // hack = eating everything after the first title
                        )
                    )
                )
            )
        )
    )
}
