package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs
import ch.deletescape.highlight.core.keyword
import ch.deletescape.highlight.core.keywords

/*
Language = Monkey
Author = Arthur Bikmullin <devolonter@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/monkey.js MD5 <cc5fe426f08bf1f0db6a8980eeb76bff>
 */
internal fun monkey(): Mode {
    var NUMBER = Mode(
        className = "number",
        relevance = 0,
        variants = listOf(
            Mode(
                begin = "[\$][a-fA-F0-9]+"
            ),
            hljs.NUMBER_MODE
        )
    )
    return Mode(
        case_insensitive = true,
        keywords = listOf(
            keyword(
                className = "keyword",
                value = "public private property continue exit extern new try catch eachin not abstract final select case default const local global field end if then else elseif endif while wend repeat until forever for to step next return module inline throw import"
            ),
            keyword(
                className = "built_in",
                value = "DebugLog DebugStop Error Print ACos ACosr ASin ASinr ATan ATan2 ATan2r ATanr Abs Abs Ceil Clamp Clamp Cos Cosr Exp Floor Log Max Max Min Min Pow Sgn Sgn Sin Sinr Sqrt Tan Tanr Seed PI HALFPI TWOPI"
            ),
            keyword(
                className = "literal",
                value = "true false null and or shl shr mod"
            )
        ).flatten(),
        illegal =
        """\/\*""",
        contains = listOf(
            hljs.COMMENT(
                "#rem",
                "#end"
            ),
            hljs.COMMENT(
                "'",
                "${'$'}",
                Mode(
                    relevance = 0
                )
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function method"),
                end = "[(=:]|${'$'}",
                illegal =
                """\n""",
                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end = "$",
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends implements")
                    ),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                className = "built_in",
                begin = "\\b(self|super)\\b"
            ),
            Mode(
                className = "meta",
                begin = "\\s*#",
                end = "$",
                keywords =
                keyword(
                    className = "meta-keyword",
                    value = "if else elseif endif end then"
                )
            ),
            Mode(
                className = "meta",
                begin = "^\\s*strict\\b"
            ),
            Mode(
                beginKeywords = keywords("alias"),
                end = "=",
                contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
            ),
            hljs.QUOTE_STRING_MODE,
            NUMBER
        )
    )
}
