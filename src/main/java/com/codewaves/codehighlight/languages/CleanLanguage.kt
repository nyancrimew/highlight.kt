package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Clean
Author = Camil Staps <info@camilstaps.nl>
Category = functional
Website = http://clean.cs.ru.nl
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CleanLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("clean\",\"icl\",\"dcl"),
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "if let in with where case of class instance otherwise implementation definition system module from import qualified as special code inline foreign export ccall stdcall generic derive infix infixl infixr"
            ),
            Keyword(
                className = "built_in",

                value = "Int Real Char Bool"
            ),
            Keyword(
                className = "literal",

                value = "True False"
            )
        ),
        contains = listOf(

            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,

            Mode(begin = "->|<-[|:]?|#!?|>>=|\\{\\||\\|\\}|:==|=:|<>") // relevance booster
        )
    )
}
