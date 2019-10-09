package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = AppleScript
Authors = Nathan Grigg <nathan@nathanamy.org>, Dr. Drang <drdrang@gmail.com>
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/applescript.js MD5 <1dcb3bdabfd482de30648d0a7294e851>
 */
internal fun applescript(): Mode {
    var STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = ""))
    var PARAMS = Mode(
        className = "params",
        begin = "\\(",
        end = "\\)",
        contains = listOf(
            hljs.SELF,
            hljs.C_NUMBER_MODE, STRING
        )
    )
    var COMMENT_MODE_1 = hljs.COMMENT(
        "--",
        "\$"
    )
    var COMMENT_MODE_2 = hljs.COMMENT(
        "\\(\\*",
        "\\*\\)",
        Mode(
            contains = listOf(
                hljs.SELF,
                COMMENT_MODE_1
            ) // allow nesting
        )
    )
    var COMMENTS = listOf(
        COMMENT_MODE_1,
        COMMENT_MODE_2,
        hljs.HASH_COMMENT_MODE
    )
    return Mode(
        aliases = listOf("osascript"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    "about above after against and around as at back before beginning behind below beneath beside between but by considering contain contains continue copy div does eighth else end equal equals error every exit fifth first for fourth from front get given global if ignoring in into is it its last local me middle mod my ninth not of on onto or over prop property put ref reference repeat returning script second set seventh since sixth some tell tenth that the|0 then third through thru timeout times to transaction try until where while whose with without"
            ),
            Keyword(
                className = "literal",
                value =
                    "AppleScript false linefeed return pi quote result space tab true"
            ),
            Keyword(
                className = "built_in",
                value =
                    "alias application boolean class constant date file integer list number real record string text activate beep count delay launch log offset read round run say summarize write character characters contents day frontmost id item length month name paragraph paragraphs rest reverse running time version weekday word words year"
            )
        ),
        contains = listOf(
            STRING,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "built_in",
                begin =
                    "\\b(clipboard info|the clipboard|info for|list (disks|folder)|mount volume|path to|(close|open for) access|(get|set) eof|current date|do shell script|get volume settings|random number|set volume|system attribute|system info|time to GMT|(load|run|store) script|scripting components|ASCII (character|number)|localized string|choose (application|color|file|file name|folder|from list|remote application|URL)|display (alert|dialog))\\b|^\\s*return\\b"
            ),
            Mode(
                className = "literal",
                begin =
                    "\\b(text item delimiters|current application|missing value)\\b"
            ),
            Mode(
                className = "keyword",
                begin =
                    "\\b(apart from|aside from|instead of|out of|greater than|isn't|(doesn't|does not) (equal|come before|come after|contain)|(greater|less) than( or equal)?|(starts?|ends|begins?) with|contained by|comes (before|after)|a (ref|reference)|POSIX file|POSIX path|(date|time) string|quoted form)\\b"
            ),
            Mode(
                beginKeywords = keywords("on"),
                illegal = "[\${=;\\n]",
                contains = listOf(hljs.UNDERSCORE_TITLE_MODE, PARAMS)
            )
        ) + COMMENTS,
        illegal = "//|->|=>|\\[\\["
    )
}
