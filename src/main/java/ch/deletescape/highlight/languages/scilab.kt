package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Scilab
Author = Sylvestre Ledru <sylvestre.ledru@scilab-enterprises.com>
Origin = matlab.js
Description = Scilab is a port from Matlab
Category = scientific
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/scilab.js MD5 <306ca1533ab3523d511f38c93f2e5f96>
 */
internal fun scilab(): Mode {
    var COMMON_CONTAINS = listOf(
        hljs.C_NUMBER_MODE,
        Mode(
            className = "string",
            begin = "'|\"",
            end = "'|\"",
            contains = listOf(hljs.BACKSLASH_ESCAPE, Mode(begin = "''"))
        )
    )
    return Mode(
        aliases = listOf("sci"),
        lexemes =
            """%?\w+""",
        keywords = listOf(
            keyword(
                className = "keyword",
                value = "abort break case clear catch continue do elseif else endfunction end for function global if pause return resume select try then while"
            ),
            keyword(
                className = "literal",
                value =
                    "%f %F %t %T %pi %eps %inf %nan %e %i %z %s"
            ),
            keyword(
                className = "built_in",
                value = // Scilab has more than 2000 functions. Just list the most commons
                    "abs and acos asin atan ceil cd chdir clearglobal cosh cos cumprod deff disp error exec execstr exists exp eye gettext floor fprintf fread fsolve imag isdef isempty isinfisnan isvector lasterror length load linspace list listfiles log10 log2 log max min msprintf mclose mopen ones or pathconvert poly printf prod pwd rand real round sinh sin size gsort sprintf sqrt strcat strcmps tring sum system tanh tan type typename warning zeros matrix"
            )
        ).flatten(),
        illegal = "(\"|#|/\\*|\\s+/\\w+)",
        contains = listOf(
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end = "$",
                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE,
                    Mode(
                        className = "params",
                        begin = "\\(",
                        end = "\\)"
                    )
                )
            ),
            Mode(
                begin = "[a-zA-Z_][a-zA-Z_0-9]*('+[\\.']*|[\\.']+)",
                end = "",
                relevance = 0
            ),
            Mode(
                begin = "\\[",
                end = "\\]'*[\\.']*",
                relevance = 0,
                contains = COMMON_CONTAINS
            ),
            hljs.COMMENT(
                "//",
                "\$"
            )
        ) + COMMON_CONTAINS
    )
}
