package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Nix
Author = Domen Kožar <domen@dev.si>
Description = Nix functional language. See http = //nixos.org/nix
*/

/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/nix.js MD5 <44dc13e1cde693c3c4bc18ab9549c386>
 */
internal fun nix(): Mode {
    var NIX_KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "rec with let in inherit assert if else then"
        ),
        keyword(
            className = "literal",
            value =
                "true false or and null"
        ),
        keyword(
            className = "built_in",
            value =
                "import abort baseNameOf dirOf isNull builtins map removeAttrs throw toString derivation"
        )
    ).flatten()
    var ANTIQUOTE = Mode(
        className = "subst",
        begin =
            """\${'$'}\{""",
        end =
            """}""",
        keywords = keywords(NIX_KEYWORDS)
    )
    var ATTRS = Mode(
        begin =
            """[a-zA-Z0-9-_]+(\s*=)""",
        returnBegin = true,
        relevance = 0,
        contains = listOf(
            Mode(
                className = "attr",
                begin =
                    """\S+"""
            )
        )
    )
    var STRING = Mode(
        className = "string",
        contains = listOf(ANTIQUOTE),
        variants = listOf(
            Mode(
                begin = "''",
                end = "''"
            ),
            Mode(
                begin = "\"",
                end = "\""
            )
        )
    )
    var EXPRESSIONS = listOf(
        hljs.NUMBER_MODE,
        hljs.HASH_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        STRING,
        ATTRS
    )
    ANTIQUOTE.contains = EXPRESSIONS
    return Mode(
        aliases = listOf("nixos"),
        keywords = keywords(NIX_KEYWORDS),
        contains = EXPRESSIONS
    )
}
