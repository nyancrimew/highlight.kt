package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Properties
Contributors = Valentin Aitken <valentin@nalisbg.com>, Egor Rogov <e.rogov@postgrespro.ru>
Category = common, config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/properties.js MD5 <c3a2ab075bc64a66c7e92bf798250736>
 */
internal fun properties(): Mode {
    // whitespaces: space, tab, formfeed
    var WS0 = "[ \\t\\f]*"
    var WS1 = "[ \\t\\f]+"
    // delimiter
    var DELIM = "(" +
        WS0 + "[:=]'+WS0+ '|" +
        WS1 + ")"
    var KEY_ALPHANUM = "([^\\\\\\W = = \\t\\f\\n]|\\\\.)+"
    var KEY_OTHER = "([^\\\:= \\t\\f\\n]|\\\\.)+"
    var DELIM_AND_VALUE = Mode(
        // skip DELIM
        end = DELIM,
        relevance = 0,
        starts = Mode(
            // value: everything until end of line (again, taking into account backslashes)
            className = "string",
            end =
                """${'$'}""",
            relevance = 0,
            contains = listOf(
                Mode(begin = "\\\\\\n")
            )
        )
    )
    return Mode(
        case_insensitive = true,
        illegal =
            """\S""",
        contains = listOf(
            hljs.COMMENT(
                "^\\s*[!#]",
                "\$"
            ),
            // key: everything until whitespace or = or : (taking into account backslashes)
            // case of a "normal" key
            Mode(
                begin = KEY_ALPHANUM + DELIM,
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "attr",
                        begin = KEY_ALPHANUM,
                        endsParent = true,
                        relevance = 0
                    )
                ),
                starts = DELIM_AND_VALUE
            ),
            // case of key containing non-alphanumeric chars => relevance = 0
            Mode(
                begin = KEY_OTHER + DELIM,
                returnBegin = true,
                relevance = 0,
                contains = listOf(
                    Mode(
                        className = "meta",
                        begin = KEY_OTHER,
                        endsParent = true,
                        relevance = 0
                    )
                ),
                starts = DELIM_AND_VALUE
            ),
            // case of an empty key
            Mode(
                className = "attr",
                relevance = 0,
                begin = KEY_OTHER + WS0 + "\$"
            )
        )
    )
}
