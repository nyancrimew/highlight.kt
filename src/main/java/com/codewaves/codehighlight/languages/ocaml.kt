package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = OCaml
Author = Mehdi Dogguy <mehdi@dogguy.org>
Contributors = Nicolas Braud-Santoni <nicolas.braud-santoni@ens-cachan.fr>, Mickael Delahaye <mickael.delahaye@gmail.com>
Description = OCaml language definition.
Category = functional
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/ocaml.js MD5 <6a1e4a5a024dc8c97e0828ec65c79eda>
 */
internal fun ocaml(): Mode {
    /* missing support for heredoc-like string (OCaml 4.0.2+) */
    return Mode(
        aliases = listOf("ml"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    "and as assert asr begin class constraint do done downto else end exception external for fun function functor if in include inherit! inherit initializer land lazy let lor lsl lsr lxor match method!|10 method mod module mutable new object of open! open or private rec sig struct then to try type val! val virtual when while with parser value"
            ),
            Keyword(
                className = "built_in",
                value =
                    /* built-in types */
                    "array bool bytes char exn|5 float int int32 int64 list lazy_t|5 nativeint|5 string unit in_channel out_channel ref"
            ),
            Keyword(
                className = "literal",
                value =
                    "true false"
            )
        ),
        illegal =
            """\/\/|>>""",
        lexemes = "[a-z_]\\w*!?",
        contains = listOf(
            Mode(
                className = "literal",
                begin = "\\[(\\|\\|)?\\]|\\(\\)",
                relevance = 0
            ),
            hljs.COMMENT(
                "\\(\\*",
                "\\*\\)",
                Mode(
                    contains = listOf(hljs.SELF)
                )
            ),
            Mode(/* type variable */
                className = "symbol",
                begin = "'[A-Za-z_](?!\")[\\w\"]*"
                /* the grammar is ambiguous on how "a'b should be interpreted but not the compiler */
            ),
            Mode(/* polymorphic variant */
                className = "type",
                begin = "`[A-Z][\\w\"]*"
            ),
            Mode(/* module or constructor */
                className = "type",
                begin = "\\b[A-Z][\\w\"]*",
                relevance = 0
            ),
            Mode(/* don't color identifiers, but safely catch all identifiers with "*/
                begin = "[a-z_]\\w*'[\\w\"]*",
                relevance = 0
            ),
            hljs.inherit(
                hljs.APOS_STRING_MODE,
                Mode(
                    className = "string",
                    relevance = 0
                )
            ),
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
            Mode(
                className = "number",
                begin =
                    "\\b(0[xX][a-fA-F0-9_]+[Lln]?|0[oO][0-7_]+[Lln]?|0[bB][01_]+[Lln]?|[0-9][0-9_]*([Lln]|(\\.[0-9_]*)?([eE][-+]?[0-9_]+)?)?)",
                relevance = 0
            ),
            Mode(
                begin =
                    """[-=]>""" // relevance booster
            )
        )
    )
}
