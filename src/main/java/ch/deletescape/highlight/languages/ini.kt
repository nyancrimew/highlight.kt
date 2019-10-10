package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Ini, TOML
Contributors = Guillaume Gomez <guillaume1.gomez@gmail.com>
Category = common, config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/ini.js MD5 <51834d1b8f514f1148f17d09b62967ea>
 */
internal fun ini(): Mode {
    var STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE),
        variants = listOf(
            Mode(
                begin = "'''",
                end = "'''",
                relevance = 10
            ),
            Mode(
                begin = "\"\"\"",
                end = "\"\"\"",
                relevance = 10
            ),
            Mode(
                begin = "\"",
                end = "\""
            ),
            Mode(
                begin = "'",
                end = "'"
            )
        )
    )
    return Mode(
        aliases = listOf("toml"),
        case_insensitive = true,
        illegal =
            """\S""",
        contains = listOf(
            hljs.COMMENT(
                ";",
                "\${'\$'}"
            ),
            hljs.HASH_COMMENT_MODE,
            Mode(
                className = "section",
                begin =
                    """^\s*\[+""",
                end =
                    """\]+"""
            ),
            Mode(
                begin =
                    """^[a-z0-9\[\]_\.-]+\s*=\s*""",
                end = "\${'\$'}",
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "attr",
                        begin =
                            """[a-z0-9\[\]_\.-]+"""
                    ),
                    Mode(
                        begin =
                            """=""",
                        endsWithParent = true,
                        relevance = 0,
                        contains = listOf(
                            hljs.COMMENT(
                                ";",
                                "\${'\$'}"
                            ),
                            hljs.HASH_COMMENT_MODE,
                            Mode(
                                className = "literal",
                                begin =
                                    """\bon|off|true|false|yes|no\b"""
                            ),
                            Mode(
                                className = "variable",
                                variants = listOf(
                                    Mode(begin =
                                            """\${'$'}[\w\d"][\w\d_]*\"""),
                                    Mode(begin =
                                            """\${'$'}\{(.*?)}""")
                                )
                            ),
                            STRING,
                            Mode(
                                className = "number",
                                begin =
                                    """([\+\-]+)?[\d]+_[\d_]+"""
                            ),
                            hljs.NUMBER_MODE
                        )
                    )
                )
            )
        )
    )
}
