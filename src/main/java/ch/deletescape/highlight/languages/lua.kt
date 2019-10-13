package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.*
import ch.deletescape.highlight.core.hljs

/*
Language = Lua
Author = Andrew Fedorov <dmmdrs@mail.ru>
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/lua.js MD5 <b2916995c0ab9af36d9706ff801a98a0>
 */
internal fun lua(): Mode {
    var OPENING_LONG_BRACKET = "\\[=*\\["
    var CLOSING_LONG_BRACKET = "\\]=*\\]"
    var LONG_BRACKETS = Mode(
        begin = OPENING_LONG_BRACKET,
        end = CLOSING_LONG_BRACKET,
        contains = listOf(hljs.SELF)
    )
    var COMMENTS = listOf(
        hljs.COMMENT(
            "--(?!" +
                OPENING_LONG_BRACKET + ")",
            "$"
        ),
        hljs.COMMENT(
            "--" +
                OPENING_LONG_BRACKET,
            CLOSING_LONG_BRACKET,
            Mode(
                contains = listOf(LONG_BRACKETS),
                relevance = 10
            )
        )
    )
    return Mode(
        lexemes = hljs.UNDERSCORE_IDENT_RE,
        keywords = listOf(
            keyword(
                className = "literal",
                value = "true false nil"
            ),
            keyword(
                className = "keyword",
                value = "and break do else elseif end for goto if in local not or repeat return then until while"
            ),
            keyword(
                className = "built_in",
                value =
                    // Metatags and globals:
                    "_G _ENV _VERSION __index __newindex __mode __call __metatable __tostring __len __gc __add __sub __mul __div __mod __pow __concat __unm __eq __lt __le assert collectgarbage dofile error getfenv getmetatable ipairs load loadfile loadstringmodule next pairs pcall print rawequal rawget rawset require select setfenvsetmetatable tonumber tostring type unpack xpcall arg selfcoroutine resume yield status wrap create running debug getupvalue debug sethook getmetatable gethook setmetatable setlocal traceback setfenv getinfo setupvalue getlocal getregistry getfenv io lines write close flush open output type read stderr stdin input stdout popen tmpfile math log max acos huge ldexp pi cos tanh pow deg tan cosh sinh random randomseed frexp ceil floor rad abs sqrt modf asin min mod fmod log10 atan2 exp sin atan os exit setlocale date getenv difftime remove time clock tmpname rename execute package preload loadlib loaded loaders cpath config path seeall string sub upper len gfind rep find match char dump gmatch reverse byte format gsub lower table setn insert getn foreachi maxn foreach concat sort remove"
            )
        ).flatten(),
        contains = COMMENTS + listOf(
            Mode(
                className = "function",
                beginKeywords = keywords(
                    "function"
                ),
                end = "\\)",
                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = "([_a-zA-Z]\\w*\\.)*([_a-zA-Z]\\w*:)?[_a-zA-Z]\\w*")),
                    Mode(
                        className = "params",
                        begin = "\\(",
                        endsWithParent = true,
                        contains = COMMENTS
                    )
                ) + COMMENTS
            ),
            hljs.C_NUMBER_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "string",
                begin = OPENING_LONG_BRACKET,
                end = CLOSING_LONG_BRACKET,
                contains = listOf(LONG_BRACKETS),
                relevance = 5
            )
        )
    )
}
