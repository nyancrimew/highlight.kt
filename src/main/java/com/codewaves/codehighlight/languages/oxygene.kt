package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Oxygene
Author = Carlo Kok <ck@remobjects.com>
Description = Language definition for RemObjects Oxygene (http = //www.remobjects.com)
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/oxygene.js MD5 <113c7b737d05b098a8ad12ac69612ddc>
 */
internal fun oxygene(): Mode {
    var OXYGENE_KEYWORDS = "abstract add and array as asc aspect assembly async begin break block by case class concat const copy constructor continue create default delegate desc distinct div do downto dynamic each else empty end ensure enum equals event except exit extension external false final finalize finalizer finally flags for forward from function future global group has if implementation implements implies in index inherited inline interface into invariants is iterator join locked locking loop matching method mod module namespace nested new nil not notify nullable of old on operator or order out override parallel params partial pinned private procedure property protected public queryable raise read readonly record reintroduce remove repeat require result reverse sealed select self sequence set shl shr skip static step soft take then to true try tuple type union unit unsafe until uses using var virtual raises volatile where while with write xor yield await mapped deprecated stdcall cdecl pascal register safecall overload library platform reference packed strict published autoreleasepool selector strong weak unretained"
    var CURLY_COMMENT = hljs.COMMENT(
        "{",
        "}",
        Mode(
            relevance = 0
        )
    )
    var PAREN_COMMENT = hljs.COMMENT(
        "\\(\\*",
        "\\*\\)",
        Mode(
            relevance = 10
        )
    )
    var STRING = Mode(
        className = "string",
        begin = "'",
        end = "'",
        contains = listOf(Mode(begin = "''"))
    )
    var CHAR_STRING = Mode(
        className = "string",
        begin = "(#\\d+)+"
    )
    var FUNCTION = Mode(
        className = "function",
        beginKeywords = keywords("function constructor destructor procedure method"),
        end = "[:;]",
        keywords = keywords("function constructor|10 destructor|10 procedure|10 method|10"),
        contains = listOf(
            hljs.TITLE_MODE,
            Mode(
                className = "params",
                begin = "\\(",
                end = "\\)",
                keywords = keywords(OXYGENE_KEYWORDS),
                contains = listOf(STRING, CHAR_STRING)
            ),
            CURLY_COMMENT, PAREN_COMMENT
        )
    )
    return Mode(
        case_insensitive = true,
        lexemes =
            """\.?\w+""",
        keywords = keywords(OXYGENE_KEYWORDS),
        illegal = "(\"|\\$[G-Zg-z]|\\/\\*|</|=>|->)",
        contains = listOf(
            CURLY_COMMENT, PAREN_COMMENT, hljs.C_LINE_COMMENT_MODE,
            STRING, CHAR_STRING,
            hljs.NUMBER_MODE,
            FUNCTION,
            Mode(
                className = "class",
                begin = "=\\bclass\\b",
                end = "end;",
                keywords = keywords(OXYGENE_KEYWORDS),
                contains = listOf(
                    STRING, CHAR_STRING,
                    CURLY_COMMENT, PAREN_COMMENT, hljs.C_LINE_COMMENT_MODE,
                    FUNCTION
                )
            )
        )
    )
}
