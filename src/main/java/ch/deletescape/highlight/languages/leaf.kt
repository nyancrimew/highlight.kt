package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Leaf
Author = Hale Chan <halechan@qq.com>
Description = Based on the Leaf reference from https = //vapor.github.io/documentation/guide/leaf.html.
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/leaf.js MD5 <a36d1b9b8a9745d75ea8a935652c3cac>
 */
internal fun leaf(): Mode {
    return Mode(
        contains = listOf(
            Mode(
                className = "function",
                begin = "#+[A-Za-z_0-9]*\\(",
                end = " \\{",
                returnBegin = true,
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        className = "keyword",
                        begin = "#+"
                    ),
                    Mode(
                        className = "title",
                        begin = "[A-Za-z_][A-Za-z_0-9]*"
                    ),
                    Mode(
                        className = "params",
                        begin = "\\(",
                        end = "\\)",
                        endsParent = true,
                        contains = listOf(
                            Mode(
                                className = "string",
                                begin = "\"",
                                end = "\""
                            ),
                            Mode(
                                className = "variable",
                                begin = "[A-Za-z_][A-Za-z_0-9]*"
                            )
                        )
                    )
                )
            )
        )
    )
}
