package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Parser3
Requires = xml.js
Author = Oleg Volchkov <oleg@volchkov.net>
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/parser3.js MD5 <af8de20e7ac586dac61005a9e2967ca7>
 */
internal fun parser3(): Mode {
    var CURLY_SUBCOMMENT = hljs.COMMENT(
        "{",
        "}",
        Mode(
            contains = listOf(hljs.SELF)
        )
    )
    return Mode(
        subLanguage = "xml",
        relevance = 0,
        contains = listOf(
            hljs.COMMENT(
                "^#",
                "\$"
            ),
            hljs.COMMENT(
                "\\^rem{",
                "}",
                Mode(
                    relevance = 10,
                    contains = listOf(
                        CURLY_SUBCOMMENT
                    )
                )
            ),
            Mode(
                className = "meta",
                begin = "^@(?:BASE|USE|CLASS|OPTIONS)\$",
                relevance = 10
            ),
            Mode(
                className = "title",
                begin = "@[\\w\\-]+\\[[\\w^;\\-]*\\](?:\\[[\\w^;\\-]*\\))?(?:.*)\$"
            ),
            Mode(
                className = "variable",
                begin = "\\\$\\{?[\\w\\-\\.\:]+\\}?"
            ),
            Mode(
                className = "keyword",
                begin = "\\^[\\w\\-\\.\:]+"
            ),
            Mode(
                className = "number",
                begin = "\\^#[0-9a-fA-F]+"
            ),
            hljs.C_NUMBER_MODE
        )
    )
}
