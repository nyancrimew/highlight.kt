package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = VB.NET
Author = Poren Chiang <ren.chiang@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class VbnetLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("vb"),
        case_insensitive = true,
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "addhandler addressof alias and andalso aggregate ansi as async assembly auto await binary by byref byval " +
                    /* a-b */
                    "call case catch class compare const continue custom declare default delegate dim distinct do " +
                    /* c-d */
                    "each equals else elseif end enum erase error event exit explicit finally for friend from function " +
                    /* e-f */
                    "get global goto group handles if implements imports in inherits interface into is isfalse isnot istrue iterator " +
                    /* g-i */
                    "join key let lib like loop me mid mod module mustinherit mustoverride mybase myclass " +
                    /* j-m */
                    "namespace narrowing new next not notinheritable notoverridable " +
                    /* n */
                    "of off on operator option optional or order orelse overloads overridable overrides " +
                    /* o */
                    "paramarray partial preserve private property protected public " +
                    /* p */
                    "raiseevent readonly redim rem removehandler resume return " +
                    /* r */
                    "select set shadows shared skip static step stop structure strict sub synclock " +
                    /* s */
                    "take text then throw to try unicode until using when where while widening with withevents writeonly xor yield"
            ),
            /* t-y */
            built_in = "boolean byte cbool cbyte cchar cdate cdec cdbl char cint clng cobj csbyte cshort csng cstr ctype " +
                /* b-c */
                "date decimal directcast double gettype getxmlnamespace iif integer long object " +
                /* d-o */
                "sbyte short single string trycast typeof uinteger ulong ushort",
            /* s-u */
            literal = "true false nothing"
        ),
        illegal = "//|{|}|endif|gosub|variant|wend|^\\$ ",
        /* reserved deprecated keywords */
        contains = listOf(
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(contains = listOf(Mode(begin = "\"\"")))),
            hljs.COMMENT(
                "\'",

                "\$",

                Mode(
                    returnBegin = true,
                    contains = listOf(
                        Mode(
                            className = "doctag",

                            begin = "\'\'\'|<!--|-->",

                            contains = listOf(hljs.PHRASAL_WORDS_MODE)
                        ),
                        Mode(
                            className = "doctag",

                            begin = "</?",

                            end = ">",

                            contains = listOf(hljs.PHRASAL_WORDS_MODE)
                        )
                    )
                )
            ),
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",

                begin = "#",

                end = "\$",

                keywords = keywords(
                    listOf(
                        Keyword(
                            className = "meta-keyword",

                            value = "if else elseif end region externalsource"
                        )
                    )
                )
            )
        )
    )
}
