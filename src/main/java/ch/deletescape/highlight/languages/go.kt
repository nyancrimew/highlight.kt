package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Go
Author = Stephan Kountso aka StepLg <steplg@gmail.com>
Contributors = Evgeny Stepanischev <imbolk@gmail.com>
Description = Google go language (golang). For info about language see http = //golang.org/
Category = system
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/go.js MD5 <91e5b436267d0bff825b2b67e9e6312b>
 */
internal fun go(): Mode {
    var GO_KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                "break default func interface select case map struct chan else goto package switch const fallthrough if range type continue for import return var go defer bool byte complex64 complex128 float32 float64 int8 int16 int32 int64 string uint8 uint16 uint32 uint64 int uint uintptr rune"
        ),
        Keyword(
            className = "literal",
            value =
                "true false iota nil"
        ),
        Keyword(
            className = "built_in",
            value =
                "append cap close complex copy imag len make new panic print println real recover delete"
        )
    )
    return Mode(
        aliases = listOf("golang"),
        keywords = keywords(GO_KEYWORDS),
        illegal = "</",
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "string",
                variants = listOf(
                    hljs.QUOTE_STRING_MODE,
                    Mode(
                        begin = "'",
                        end = "[^\\\\]'"
                    ),
                    Mode(
                        begin = "`",
                        end = "`"
                    )
                )
            ),
            Mode(
                className = "number",
                variants = listOf(
                    Mode(
                        begin = hljs.C_NUMBER_RE + "[i]",
                        relevance = 1
                    ),
                    hljs.C_NUMBER_MODE
                )
            ),
            Mode(
                begin =
                    """:=""" // relevance booster
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("func"),
                end =
                    """\s*\{""",
                excludeEnd = true,
                contains = listOf(
                    hljs.TITLE_MODE,
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        keywords = keywords(GO_KEYWORDS),
                        illegal =
                            """[""]\"""
                    )
                )
            )
        )
    )
}
