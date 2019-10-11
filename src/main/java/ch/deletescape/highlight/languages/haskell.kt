package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Haskell
Author = Jeremy Hull <sourdrums@gmail.com>
Contributors = Zena Treep <zena.treep@gmail.com>
Category = functional
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/haskell.js MD5 <22dbe4aa16f2388ad23d35ebb1f9f77b>
 */
internal fun haskell(): Mode {
    var COMMENT = Mode(
        variants = listOf(
            hljs.COMMENT(
                "--",
                "\$"
            ),
            hljs.COMMENT(
                "\\{-",
                "-\\}",
                Mode(
                    contains = listOf(hljs.SELF)
                )
            )
        )
    )
    var PRAGMA = Mode(
        className = "meta",
        begin = "\\{-#",
        end = "#-\\}"
    )
    var PREPROCESSOR = Mode(
        className = "meta",
        begin = "^#",
        end = "\$"
    )
    var CONSTRUCTOR = Mode(
        className = "type",
        begin = "\\b[A-Z][\\w']*",
        // TODO: other constructors (build-in, infix).
        relevance = 0
    )
    var LIST = Mode(
        begin = "\\(",
        end = "\\)",
        illegal = "\"",
        contains = listOf(
            PRAGMA,
            PREPROCESSOR,
            Mode(
                className = "type",
                begin = "\\b[A-Z][\\w]*(\\((\\.\\.|,|\\w+)\\))?"
            ),
            hljs.inherit(hljs.TITLE_MODE, Mode(begin = "[_a-z][\\w']*")),
            COMMENT
        )
    )
    var RECORD = Mode(
        begin = "\\{",
        end = "}",
        contains = LIST.contains
    )
    return Mode(
        aliases = listOf("hs"),
        keywords =
            keywords("let in if then else case of where do module import hiding qualified type data newtype deriving class instance as default infix infixl infixr foreign export ccall stdcall cplusplus jvm dotnet safe unsafe family forall mdo proc rec"),
        contains = listOf(
            // Top-level constructions.

            Mode(
                beginKeywords = keywords("module"),
                end = "where",
                keywords = keywords("module where"),
                contains = listOf(LIST, COMMENT),
                illegal = "\\W\\.|;"
            ),
            Mode(
                begin = "\\bimport\\b",
                end = "\$",
                keywords = keywords("import qualified as hiding"),
                contains = listOf(LIST, COMMENT),
                illegal = "\\W\\.|;"
            ),
            Mode(
                className = "class",
                begin = "^(\\s*)?(class|instance)\\b",
                end = "where",
                keywords = keywords("class family instance where"),
                contains = listOf(CONSTRUCTOR, LIST, COMMENT)
            ),
            Mode(
                className = "class",
                begin = "\\b(data|(new)?type)\\b",
                end = "\$",
                keywords = keywords("data family type newtype deriving"),
                contains = listOf(PRAGMA, CONSTRUCTOR, LIST, RECORD, COMMENT)
            ),
            Mode(
                beginKeywords = keywords("default"),
                end = "\$",
                contains = listOf(CONSTRUCTOR, LIST, COMMENT)
            ),
            Mode(
                beginKeywords = keywords("infix infixl infixr"),
                end = "\$",
                contains = listOf(hljs.C_NUMBER_MODE, COMMENT)
            ),
            Mode(
                begin = "\\bforeign\\b",
                end = "\$",
                keywords = keywords("foreign import export ccall stdcall cplusplus jvm dotnet safe unsafe"),
                contains = listOf(CONSTRUCTOR, hljs.QUOTE_STRING_MODE, COMMENT)
            ),
            Mode(
                className = "meta",
                begin = "#!\\/usr\\/bin\\/env\\ runhaskell",
                end = "\$"
            ),
            // "Whitespaces".

            PRAGMA,
            PREPROCESSOR,
            // Literals and names.

            // TODO: characters.
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,
            CONSTRUCTOR,
            hljs.inherit(hljs.TITLE_MODE, Mode(begin = "^[_a-z][\\w']*")),
            COMMENT,
            Mode(begin = "->|<-") // No markup, relevance booster
        )
    )
}
