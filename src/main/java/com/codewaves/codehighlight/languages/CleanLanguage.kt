package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.hljs
import com.codewaves.codehighlight.core.keywordsJson

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
        aliases = listOf("clean", "icl", "dcl"),
        keywords = keywordsJson(
            """
keyword = "if let in with where case of class instance otherwise implementation definition system module from import qualified as special code inline foreign export ccall stdcall generic derive infix infixl infixr",
built_in = "Int Real Char Bool",
literal = "True False"""".trimIndent()
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
