package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Smalltalk
Author = Vladimir Gubarkov <xonixx@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/smalltalk.js MD5 <ead1d495c7250707df62ef1ebac40914>
 */
internal fun smalltalk(): Mode {
    var VAR_IDENT_RE = "[a-z][a-zA-Z0-9_]*"
    var CHAR = Mode(
        className = "string",
        begin = "\\\$.{1}"
    )
    var SYMBOL = Mode(
        className = "symbol",
        begin = "#" +
            hljs.UNDERSCORE_IDENT_RE
    )
    return Mode(
        aliases = listOf("st"),
        keywords = keywords("self super nil true false thisContext"),
        // only 6
        contains = listOf(
            hljs.COMMENT(
                "\"",
                "\""
            ),
            hljs.APOS_STRING_MODE,
            Mode(
                className = "type",
                begin = "\\b[A-Z][A-Za-z0-9_]*",
                relevance = 0
            ),
            Mode(
                begin = VAR_IDENT_RE + ":",
                relevance = 0
            ),
            hljs.C_NUMBER_MODE,
            SYMBOL,
            CHAR,
            Mode(
                // This looks more complicated than needed to avoid combinatorial
                // explosion under V8. It effectively means `| var1 var2 ... |` with
                // whitespace adjacent to `|` being optional.
                begin = "\\|[ ]*" +
                    VAR_IDENT_RE + "([ ]+" +
                    VAR_IDENT_RE + ")*[ ]*\\|",
                returnBegin = true,
                end =
                    """\|""",
                illegal =
                    """\S""",
                contains = listOf(
                    Mode(
                        begin = "(\\|[ ]*)?" +
                            VAR_IDENT_RE
                    )
                )
            ),
            Mode(
                begin = "\\#\\(",
                end = "\\)",
                contains = listOf(
                    hljs.APOS_STRING_MODE,
                    CHAR,
                    hljs.C_NUMBER_MODE,
                    SYMBOL
                )
            )
        )
    )
}
