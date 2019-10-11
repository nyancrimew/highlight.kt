package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Clean
Author = Camil Staps <info@camilstaps.nl>
Category = functional
Website = http = //clean.cs.ru.nl
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/clean.js MD5 <192d55295eebe76f4d7c680a69ce3144>
 */
internal fun clean(): Mode {
    return Mode(
        aliases = listOf("clean','icl','dcl"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    "if let in with where case of class instance otherwise implementation definition system module from import qualified as special code inline foreign export ccall stdcall generic derive infix infixl infixr"
            ),
            Keyword(
                className = "built_in",
                value =
                    "Int Real Char Bool"
            ),
            Keyword(
                className = "literal",
                value =
                    "True False"
            )
        ),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,
            Mode(begin = "->|<-[|:]?|#!?|>>=|\\\\{\\||\\|\\\\}|:==|=:|<>") // relevance booster
        )
    )
}
