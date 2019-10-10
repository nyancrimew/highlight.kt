package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs

/*
Language = VB.NET
Author = Poren Chiang <ren.chiang@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/vbnet.js MD5 <19a6048635fedbb413732ecc85e8ade1>
 */
internal fun vbnet(): Mode {
    return Mode(
        aliases = listOf("vb"),
        case_insensitive = true,
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    "addhandler addressof alias and andalso aggregate ansi as async assembly auto await binary by byref byval call case catch class compare const continue custom declare default delegate dim distinct do each equals else elseif end enum erase error event exit explicit finally for friend from function get global goto group handles if implements imports in inherits interface into is isfalse isnot istrue iterator join key let lib like loop me mid mod module mustinherit mustoverride mybase myclass namespace narrowing new next not notinheritable notoverridable of off on operator option optional or order orelse overloads overridable overrides paramarray partial preserve private property protected public raiseevent readonly redim rem removehandler resume return select set shadows shared skip static step stop structure strict sub synclock take text then throw to try unicode until using when where while widening with withevents writeonly xor yield"
            ),
            /* t-y */
            Keyword(
                className = "built_in",
                value =
                    "boolean byte cbool cbyte cchar cdate cdec cdbl char cint clng cobj csbyte cshort csng cstr ctype date decimal directcast double gettype getxmlnamespace iif integer long object sbyte short single string trycast typeof uinteger ulong ushort"
            ),
            /* s-u */
            Keyword(
                className = "literal",
                value =
                    "true false nothing"
            )
        ),
        illegal = "//|{|}|endif|gosub|variant|wend|^\\\$ ", /* reserved deprecated keywords */
        contains = listOf(
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(contains = listOf(Mode(begin = "\"\"")))),
            hljs.COMMENT(
                "'",
                "\$",
                Mode(
                    returnBegin = true,
                    contains = listOf(
                        Mode(
                            className = "doctag",
                            begin = "'''|<!--|-->",
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
                keywords = listOf(
                    Keyword(
                        className = "meta-keyword",
                        value = "if else elseif end region externalsource"
                    )
                )
            )
        )
    )
}
