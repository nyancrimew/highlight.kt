package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Pony
Author = Joe Eli McIlvain <joe.eli.mac@gmail.com>
Description = Pony is an open-source, object-oriented, actor-model,
             capabilities-secure, high performance programming language.
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/pony.js MD5 <e7c4f02e15090fff8264709a68646819>
 */
internal fun pony(): Mode {
    var KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "actor addressof and as be break class compile_error compile_intrinsic consume continue delegate digestof do else elseif embed end error for fun if ifdef in interface is isnt lambda let match new not object or primitive recover repeat return struct then trait try type until use var where while with xor"
        ),
        keyword(
            className = "meta",
            value =
                "iso val tag trn box ref"
        ),
        keyword(
            className = "literal",
            value =
                "this false true"
        )
    ).flatten()
    var TRIPLE_QUOTE_STRING_MODE = Mode(
        className = "string",
        begin = "\"\"\"",
        end = "\"\"\"",
        relevance = 10
    )
    var QUOTE_STRING_MODE = Mode(
        className = "string",
        begin = "\"",
        end = "\"",
        contains = listOf(hljs.BACKSLASH_ESCAPE)
    )
    var SINGLE_QUOTE_CHAR_MODE = Mode(
        className = "string",
        begin = "'",
        end = "'",
        contains = listOf(hljs.BACKSLASH_ESCAPE),
        relevance = 0
    )
    var TYPE_NAME = Mode(
        className = "type",
        begin = "\\b_?[A-Z][\\w]*",
        relevance = 0
    )
    var PRIMED_NAME = Mode(
        begin = hljs.IDENT_RE + "'",
        relevance = 0
    )
    var NUMBER_MODE = Mode(
        className = "number",
        begin = "(-?)(\\b0[xX][a-fA-F0-9]+|\\b0[bB][01]+|(\\b\\d+(_\\d+)?(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?)",
        relevance = 0
    )
    /**
     * The `FUNCTION` and `CLASS` modes were intentionally removed to simplify
     * highlighting and fix cases like
     * ```
     * interface Iterator[A = A)
     *   fun has_next(): Bool
     *   fun next(): A?
     * ```
     * where it is valid to have a function head without a body
     */
    return Mode(
        keywords = keywords(KEYWORDS),
        contains = listOf(
            TYPE_NAME,
            TRIPLE_QUOTE_STRING_MODE,
            QUOTE_STRING_MODE,
            SINGLE_QUOTE_CHAR_MODE,
            PRIMED_NAME,
            NUMBER_MODE,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE
        )
    )
}
