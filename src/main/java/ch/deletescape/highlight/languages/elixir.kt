package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Elixir
Author = Josh Adams <josh@isotope11.com>
Description = language definition for Elixir source code files (.ex and .exs).  Based on ruby language support.
Category = functional
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/elixir.js MD5 <684f7e640a992092904e1baed057e878>
 */
internal fun elixir(): Mode {
    var ELIXIR_IDENT_RE = "[a-zA-Z_][a-zA-Z0-9_.]*(\\!|\\?)?"
    var ELIXIR_METHOD_RE = "[a-zA-Z_]\\w*[!?=]?|[-+~]\\@|<<|>>|=~|===?|<=>|[<>]=?|\\*\\*|[-/+%^&*~`|]|\\[\\]=?"
    var ELIXIR_KEYWORDS =
        "and false then defined module in return redo retry end for true self when next until do begin unless nil break not case cond alias while ensure or include use alias fn quote require import with|0"
    var SUBST = Mode(
        className = "subst",
        begin = "#\\{",
        end = "}",
        lexemes = ELIXIR_IDENT_RE,
        keywords = keywords(ELIXIR_KEYWORDS)
    )
    var STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
        variants = listOf(
            Mode(
                begin =
                    """'""",
                end =
                    """'"""
            ),
            Mode(
                begin =
                    """"""",
                end =
                    """""""
            )
        )
    )
    var FUNCTION = Mode(
        className = "function",
        beginKeywords = keywords("def defp defmacro"),
        end =
            """\B\b""", // the mode is ended by the title
        contains = listOf(
            hljs.inherit(
                hljs.TITLE_MODE,
                Mode(
                    begin = ELIXIR_IDENT_RE,
                    endsParent = true
                )
            )
        )
    )
    var CLASS = hljs.inherit(
        FUNCTION,
        Mode(
            className = "class",
            beginKeywords = keywords("defimpl defmodule defprotocol defrecord"),
            end =
                """\bdo\b|${'$'}|;"""
        )
    )
    var ELIXIR_DEFAULT_CONTAINS = listOf(
        STRING,
        hljs.HASH_COMMENT_MODE,
        CLASS,
        FUNCTION,
        Mode(
            begin = "::"
        ),
        Mode(
            className = "symbol",
            begin = ":(?![\\s = ))",
            contains = listOf(STRING, Mode(begin = ELIXIR_METHOD_RE)),
            relevance = 0
        ),
        Mode(
            className = "symbol",
            begin = ELIXIR_IDENT_RE + ":(?!:)",
            relevance = 0
        ),
        Mode(
            className = "number",
            begin = "(\\b0o[0-7_]+)|(\\b0b[01_]+)|(\\b0x[0-9a-fA-F_]+)|(-?\\b[1-9][0-9_]*(.[0-9_]+([eE][-+]?[0-9]+)?)?)",
            relevance = 0
        ),
        Mode(
            className = "variable",
            begin = "(\\\$\\W)|((\\\$|\\@\\@?)(\\w+))"
        ),
        Mode(
            begin = "->"
        ),
        Mode(// regexp container
            begin = "(" +
                hljs.RE_STARTERS_RE + ")\\s*",
            contains = listOf(
                hljs.HASH_COMMENT_MODE,
                Mode(
                    className = "regexp",
                    illegal = "\\n",
                    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
                    variants = listOf(
                        Mode(
                            begin = "/",
                            end = "/[a-z]*"
                        ),
                        Mode(
                            begin = "%r\\[",
                            end = "\\][a-z]*"
                        )
                    )
                )
            ),
            relevance = 0
        )
    )
    SUBST.contains = ELIXIR_DEFAULT_CONTAINS
    return Mode(
        lexemes = ELIXIR_IDENT_RE,
        keywords = keywords(ELIXIR_KEYWORDS),
        contains = ELIXIR_DEFAULT_CONTAINS
    )
}
