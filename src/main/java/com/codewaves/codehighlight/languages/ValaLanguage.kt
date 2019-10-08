package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Vala
Author = Antono Vasiljev <antono.vasiljev@gmail.com>
Description = Vala is a new programming language that aims to bring modern programming language features to GNOME developers without imposing any additional runtime requirements and without using a different ABI compared to applications and libraries written in C.
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ValaLanguage : LanguageBuilder {
    override fun build() = Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = // Value types
                    "char uchar unichar int uint long ulong short ushort int8 int16 int32 int64 uint8 uint16 uint32 uint64 float double bool struct enum string void weak unowned owned async signal static abstract interface override virtual delegate if while do for foreach else switch case break default return try catch public private protected internal using new this get set const stdout stdin stderr var"
            ),
            Keyword(
                className = "built_in",

                value = "DBus GLib CCode Gee Object Gtk Posix"
            ),
            Keyword(
                className = "literal",

                value = "false true null"
            )
        ),
        contains = listOf(
            Mode(
                className = "class",

                beginKeywords = keywords(
                    "class interface namespace",

                    end = "{",

                    excludeEnd = true
                ),
                illegal = "[^,:\\n\\s\\.]",

                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "string",

                begin = "\"\"\"",

                end = "\"\"\"",

                relevance = 5
            ),
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",

                begin = "^#",

                end = "\$",

                relevance = 2
            )
        )
    )
}
