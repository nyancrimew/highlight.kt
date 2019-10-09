package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Erlang
Description = Erlang is a general-purpose functional language, with strict evaluation, single assignment, and dynamic typing.
Author = Nikolay Zakharov <nikolay.desh@gmail.com>, Dmitry Kovega <arhibot@gmail.com>
Category = functional
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/erlang.js MD5 <b345c7cc92db3ded7ef125c3aa06d033>
 */
internal fun erlang(): Mode {
    var BASIC_ATOM_RE = "[a-z\"][a-zA-Z0-9_\"]*"
    var FUNCTION_NAME_RE = "(\" + BASIC_ATOM_RE + \":\" + BASIC_ATOM_RE + \"|\" + BASIC_ATOM_RE + \")"
    var ERLANG_RESERVED = Mode(
        keyword = "after and andalso|10 band begin bnot bor bsl bzr bxor case catch cond div end fun if let not of orelse|10 query receive rem try when xor",
        literal = "false true"
    )
    var COMMENT = hljs.COMMENT("%\", \"\$")
    var NUMBER = Mode(
        className = "number",
        begin = "\\b(\\d+#[a-fA-F0-9]+|\\d+(\\.\\d+)?([eE][-+]?\\d+)?)",
        relevance = 0
    )
    var NAMED_FUN = Mode(
        begin = "fun\\s+\" + BASIC_ATOM_RE + \"/\\d+"
    )
    var FUNCTION_CALL = Mode(
        begin = FUNCTION_NAME_RE + "\\(",
        end = "\\)",
        returnBegin = true,
        relevance = 0,
        contains = listOf(
            Mode(
                begin = FUNCTION_NAME_RE,
                relevance = 0
            ),
            Mode(
                begin = "\\(",
                end = "\\)",
                endsWithParent = true,
                returnEnd = true,
                relevance = 0
                // "contains" defined later
            )
        )
    )
    var TUPLE = Mode(
        begin = "{",
        end = "}",
        relevance = 0
        // "contains" defined later
    )
    var VAR1 = Mode(
        begin = "\\b_([A-Z][A-Za-z0-9_]*)?",
        relevance = 0
    )
    var VAR2 = Mode(
        begin = "[A-Z][a-zA-Z0-9_]*",
        relevance = 0
    )
    var RECORD_ACCESS = Mode(
        begin = "#" + hljs.UNDERSCORE_IDENT_RE,
        relevance = 0,
        returnBegin = true,
        contains = listOf(
            Mode(
                begin = "#" + hljs.UNDERSCORE_IDENT_RE,
                relevance = 0
            ),
            Mode(
                begin = "{",
                end = "}",
                relevance = 0
                // "contains" defined later
            )
        )
    )
    var BLOCK_STATEMENTS = Mode(
        beginKeywords = keywords("fun receive if try case"),
        end = "end",
        keywords = keywords(ERLANG_RESERVED)
    )
    BLOCK_STATEMENTS.contains = listOf(
        COMMENT,
        NAMED_FUN,
        hljs.inherit(hljs.APOS_STRING_MODE, Mode(className = "")),
        BLOCK_STATEMENTS,
        FUNCTION_CALL,
        hljs.QUOTE_STRING_MODE,
        NUMBER,
        TUPLE,
        VAR1, VAR2,
        RECORD_ACCESS
    )
    var BASIC_MODES = listOf(
        COMMENT,
        NAMED_FUN,
        BLOCK_STATEMENTS,
        FUNCTION_CALL,
        hljs.QUOTE_STRING_MODE,
        NUMBER,
        TUPLE,
        VAR1, VAR2,
        RECORD_ACCESS
    )
    FUNCTION_CALL.contains[1]
        .contains = BASIC_MODES
    TUPLE.contains = BASIC_MODES
    RECORD_ACCESS.contains[1]
        .contains = BASIC_MODES
    var PARAMS = Mode(
        className = "params",
        begin = "\\(",
        end = "\\)",
        contains = BASIC_MODES
    )
    return Mode(
        aliases = listOf("erl"),
        keywords = keywords(ERLANG_RESERVED),
        illegal = "(</|\\*=|\\+=|-=|/\\*|\\*/|\\(\\*|\\*\\))",
        contains = listOf(
            Mode(
                className = "function",
                begin = "^\" + BASIC_ATOM_RE + \"\\s*\\(",
                end = "->",
                returnBegin = true,
                illegal = "\\(|#|//|/\\*|\\\\|:|;",
                contains = listOf(
                    PARAMS,
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = BASIC_ATOM_RE))
                ),
                starts = Mode(
                    end = ";|\\.",
                    keywords = keywords(ERLANG_RESERVED),
                    contains = BASIC_MODES
                )
            ),
            COMMENT,
            Mode(
                begin = "^-",
                end = "\\.",
                relevance = 0,
                excludeEnd = true,
                returnBegin = true,
                lexemes = "-" + hljs.IDENT_RE,
                keywords = keywords("-module -record -undef -export -ifdef -ifndef -author -copyright -doc -vsn -import -include -include_lib -compile -define -else -endif -file -behaviour -behavior -spec"),
                contains = listOf(PARAMS)
            ),
            NUMBER,
            hljs.QUOTE_STRING_MODE,
            RECORD_ACCESS,
            VAR1, VAR2,
            TUPLE,
            Mode(begin =
                    """\.${'$'}""") // relevance booster
        )
    )
}
