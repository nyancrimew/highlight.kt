package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.*
import ch.deletescape.highlight.core.hljs

/*
Language = Scala
Category = functional
Author = Jan Berkel <jan.berkel@gmail.com>
Contributors = Erik Osheim <d_m@plastic-idolatry.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/scala.js MD5 <8368a4d7200da2cfc014c30c75dcb7a0>
 */
internal fun scala(): Mode {
    var ANNOTATION = Mode(
        className = "meta",
        begin = "@[A-Za-z]+"
    )
    // used in strings for escaping/interpolation/substitution
    var SUBST = Mode(
        className = "subst",
        variants = listOf(
            Mode(begin = "\\$[A-Za-z0-9_]+"),
            Mode(
                begin = "\\$\\{",
                end = "}"
            )
        )
    )
    var STRING = Mode(
        className = "string",
        variants = listOf(
            Mode(
                begin = "\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                begin = "\"\"\"",
                end = "\"\"\"",
                relevance = 10
            ),
            Mode(
                begin = "[a-z]+\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
            ),
            Mode(
                className = "string",
                begin = "[a-z]+\"\"\"",
                end = "\"\"\"",
                contains = listOf(SUBST),
                relevance = 10
            )
        )
    )
    var SYMBOL = Mode(
        className = "symbol",
        begin = "'\\w[\\w\\d_]*(?!\")"
    )
    var TYPE = Mode(
        className = "type",
        begin = "\\b[A-Z][A-Za-z0-9_]*",
        relevance = 0
    )
    var NAME = Mode(
        className = "title",
        begin =
            """[^0-9\n\t "'(),.`{}\[\]:;][^\n\t "'(),.`{}\[\]:;]+|[^0-9\n\t "'(),.`{}\[\]:;=]""",
        relevance = 0
    )
    var CLASS = Mode(
        className = "class",
        beginKeywords = keywords("class object trait type"),
        end =
            """[:=\{\[\n;]""",
        excludeEnd = true,
        contains = listOf(
            Mode(
                beginKeywords = keywords("extends with"),
                relevance = 10
            ),
            Mode(
                begin =
                    """\[""",
                end =
                    """\]""",
                excludeBegin = true,
                excludeEnd = true,
                relevance = 0,
                contains = listOf(TYPE)
            ),
            Mode(
                className = "params",
                begin =
                    """\(""",
                end =
                    """\)""",
                excludeBegin = true,
                excludeEnd = true,
                relevance = 0,
                contains = listOf(TYPE)
            ),
            NAME
        )
    )
    var METHOD = Mode(
        className = "function",
        beginKeywords = keywords("def"),
        end =
            """[:=\{\[(\n;]""",
        excludeEnd = true,
        contains = listOf(NAME)
    )
    return Mode(
        keywords = listOf(
            keyword(
                className = "literal",
                value = "true false null"
            ),
            keyword(
                className = "keyword",
                value = "type yield lazy override def with val var sealed abstract private trait object if forSome for while throw finally protected extends import final return else break new catch super class case package default try this match continue throws implicit"
            )
        ).flatten(),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            STRING,
            SYMBOL,
            TYPE,
            METHOD,
            CLASS,
            hljs.C_NUMBER_MODE,
            ANNOTATION
        )
    )
}
