package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Lisp
Description = Generic lisp syntax
Author = Vasily Polovnyov <vast@whiteants.net>
Category = lisp
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/lisp.js MD5 <ceb2e573e0745639aeeea1784382d8dd>
 */
internal fun lisp(): Mode {
    var LISP_IDENT_RE = "[a-zA-Z_\\-\\+\\*\\/\\<\\=\\>\\&\\#][a-zA-Z0-9_\\-\\+\\*\\/\\<\\=\\>\\&\\#!]*"
    var MEC_RE = "\\|[^]*?\\|"
    var LISP_SIMPLE_NUMBER_RE = "(\\-|\\+)?\\d+(\\.\\d+|\\/\\d+)?((d|e|f|l|s|D|E|F|L|S)(\\+|\\-)?\\d+)?"
    var SHEBANG = Mode(
        className = "meta",
        begin = "^#!",
        end = "\$"
    )
    var LITERAL = Mode(
        className = "literal",
        begin = "\\b(t{1}|nil)\\b"
    )
    var NUMBER = Mode(
        className = "number",
        variants = listOf(
            Mode(
                begin = LISP_SIMPLE_NUMBER_RE,
                relevance = 0
            ),
            Mode(begin = "#(b|B)[0-1]+(/[0-1]+)?"),
            Mode(begin = "#(o|O)[0-7]+(/[0-7]+)?"),
            Mode(begin = "#(x|X)[0-9a-fA-F]+(/[0-9a-fA-F]+)?"),
            Mode(
                begin = "#(c|C)\\(" +
                    LISP_SIMPLE_NUMBER_RE + " + " + LISP_SIMPLE_NUMBER_RE,
                end = "\\)"
            )
        )
    )
    var STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null))
    var COMMENT = hljs.COMMENT(
        ";",
        "\$",
        Mode(
            relevance = 0
        )
    )
    var VARIABLE = Mode(
        begin = "\\*",
        end = "\\*"
    )
    var KEYWORD = Mode(
        className = "symbol",
        begin = "[:&]" +
            LISP_IDENT_RE
    )
    var IDENT = Mode(
        begin = LISP_IDENT_RE,
        relevance = 0
    )
    var MEC = Mode(
        begin = MEC_RE
    )
    var QUOTED_LIST = Mode(
        begin = "\\(",
        end = "\\)",
        contains = listOf(
            hljs.SELF,
            LITERAL, STRING, NUMBER, IDENT
        )
    )
    var QUOTED = Mode(
        contains = listOf(NUMBER, STRING, VARIABLE, KEYWORD, QUOTED_LIST, IDENT),
        variants = listOf(
            Mode(
                begin = "['`]\\(",
                end = "\\)"
            ),
            Mode(
                begin = "\\(quote ",
                end = "\\)",
                keywords = listOf(
                    Keyword(
                        className = "name",
                        value = "quote"
                    )
                )
            ),
            Mode(
                begin = "'" +
                    MEC_RE
            )
        )
    )
    var QUOTED_ATOM = Mode(
        variants = listOf(
            Mode(
                begin = "'" +
                    LISP_IDENT_RE
            ),
            Mode(
                begin = "#'" +
                    LISP_IDENT_RE + "(::" +
                    LISP_IDENT_RE + ")*"
            )
        )
    )
    var LIST = Mode(
        begin = "\\(\\s*",
        end = "\\)"
    )
    var BODY = Mode(
        endsWithParent = true,
        relevance = 0
    )
    LIST.contains = listOf(
        Mode(
            className = "name",
            variants = listOf(
                Mode(begin = LISP_IDENT_RE),
                Mode(begin = MEC_RE)
            )
        ),
        BODY
    )
    BODY.contains = listOf(QUOTED, QUOTED_ATOM, LIST, LITERAL, NUMBER, STRING, COMMENT, VARIABLE, KEYWORD, MEC, IDENT)
    return Mode(
        illegal =
            """\S""",
        contains = listOf(
            NUMBER,
            SHEBANG,
            LITERAL,
            STRING,
            COMMENT,
            QUOTED,
            QUOTED_ATOM,
            LIST,
            IDENT
        )
    )
}
