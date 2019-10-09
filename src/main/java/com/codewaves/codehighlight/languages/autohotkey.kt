package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = AutoHotkey
Author = Seongwon Lee <dlimpid@gmail.com>
Description = AutoHotkey language definition
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/autohotkey.js MD5 <dea1b9f9f52371f169a00cb5f145516b>
 */
internal fun autohotkey(): Mode {
    var BACKTICK_ESCAPE = Mode(
        begin = "`[\\s\\S]"
    )
    return Mode(
        case_insensitive = true,
        aliases = listOf("ahk"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "Break Continue Critical Exit ExitApp Gosub Goto New OnExit Pause return SetBatchLines SetTimer Suspend Thread Throw Until ahk_id ahk_class ahk_pid ahk_exe ahk_group"
            ),
            Keyword(
                className = "literal",
                value = "true false NOT AND OR"
            ),
            Keyword(
                className = "built_in",
                value = "ComSpec Clipboard ClipboardAll ErrorLevel"
            )
        ),
        contains = listOf(
            BACKTICK_ESCAPE,
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(contains = listOf(BACKTICK_ESCAPE))),
            hljs.COMMENT(";\", \"\$", Mode(relevance = 0)),
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "number",
                begin = hljs.NUMBER_RE,
                relevance = 0
            ),
            Mode(
                className = "variable", // subst would be the most accurate however fails the point of highlighting. variable is comparably the most accurate that actually has some effect
                begin = "%[a-zA-Z0-9#_\$@]+%"
            ),
            Mode(
                className = "built_in",
                begin = "^\\s*\\w+\\s*(,|%)"
                // I don't really know if this is totally relevant
            ),
            Mode(
                className = "title", // symbol would be most accurate however is higlighted just like built_in and that makes up a lot of AutoHotkey code
                // meaning that it would fail to highlight anything
                variants = listOf(
                    Mode(begin = "^[^\\n\";]+::(?!=)"),
                    Mode(
                        begin = "^[^\\n\";]+:(?!=)",
                        relevance = 0
                    ) // zero relevance as it catches a lot of things
                    // followed by a single ":" in many languages
                )
            ),
            Mode(
                className = "meta",
                begin = "^\\s*#\\w+",
                end = "\$",
                relevance = 0
            ),
            Mode(
                className = "built_in",
                begin = "A_[a-zA-Z0-9]+"
            ),
            Mode(
                // consecutive commas, not for highlighting but just for relevance
                begin = ",\\s*,"
            )
        )
    )
}
