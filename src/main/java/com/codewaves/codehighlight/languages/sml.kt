package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = SML
Author = Edwin Dalorzo <edwin@dalorzo.org>
Description = SML language definition.
Origin = ocaml.js
Category = functional
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/sml.js MD5 <a2107071b8028d500d13b65aabefe82e>
 */
internal fun sml(): Mode {
    return Mode(
        aliases = listOf("ml"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    /* according to Definition of Standard ML 97  */
                    "abstype and andalso as case datatype do else end eqtype exception fn fun functor handle if in include infix infixr let local nonfix of op open orelse raise rec sharing sig signature struct structure then type val with withtype where while"
            ),
            Keyword(
                className = "built_in",
                value =
                    /* built-in types according to basis library */
                    "array bool char exn int list option order real ref string substring vector unit word"
            ),
            Keyword(
                className = "literal",
                value =
                    "true false NONE SOME LESS EQUAL GREATER nil"
            )
        ),
        illegal =
            """\/\/|>>""",
        lexemes = "[a-z_]\\w*!?",
        contains = listOf(
            Mode(
                className = "literal",
                begin =
                    """\[(\|\|)?\]|\(\)""",
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
                begin = "[a-z_]\\w*'[\\w\"]*"
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
