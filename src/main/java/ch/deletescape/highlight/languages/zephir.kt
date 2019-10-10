package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
 Language = Zephir
 Author = Oleg Efimov <efimovov@gmail.com>
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/zephir.js MD5 <0ba94709af386a20d9ac15409a06a710>
 */
internal fun zephir(): Mode {
    var STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE),
        variants = listOf(
            Mode(
                begin = "b\"",
                end = "\""
            ),
            Mode(
                begin = "b'",
                end = "'"
            ),
            hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null))
        )
    )
    var NUMBER = Mode(variants = listOf(hljs.BINARY_NUMBER_MODE, hljs.C_NUMBER_MODE))
    return Mode(
        aliases = listOf("zep"),
        case_insensitive = true,
        keywords =
            keywords("and include_once list abstract global private echo interface as static endswitch array null if endwhile or const for endforeach self var let while isset public protected exit foreach throw elseif include __FILE__ empty require_once do xor return parent clone use __CLASS__ __LINE__ else break print eval new catch __METHOD__ case exception default die require __FUNCTION__ enddeclare final try switch continue endfor endif declare unset true false trait goto instanceof insteadof __DIR__ __NAMESPACE__ yield finally int uint long ulong char uchar double float bool boolean stringlikely unlikely"),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.HASH_COMMENT_MODE,
            hljs.COMMENT(
                "/\\*",
                "\\*/",
                Mode(
                    contains = listOf(
                        Mode(
                            className = "doctag",
                            begin = "@[A-Za-z]+"
                        )
                    )
                )
            ),
            hljs.COMMENT(
                "__halt_compiler.+?;",
                false,
                Mode(
                    endsWithParent = true,
                    keywords = keywords("__halt_compiler"),
                    lexemes = hljs.UNDERSCORE_IDENT_RE
                )
            ),
            Mode(
                className = "string",
                begin = "<<<['\"]?\\w+['\"]?\$",
                end = "^\\w+;",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                // swallow composed identifiers to avoid parsing them as keywords
                begin =
                    """(::|->)+[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*"""
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end =
                    """[;{]""",
                excludeEnd = true,
                illegal = "\\\${'$'}|\\[|%",
                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE,
                    Mode(
                        className = "params",
                        begin = "\\(",
                        end = "\\)",
                        contains = listOf(
                            hljs.SELF,
                            hljs.C_BLOCK_COMMENT_MODE,
                            STRING,
                            NUMBER
                        )
                    )
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end = "{",
                excludeEnd = true,
                illegal =
                    """[:\(\${'$'}"]""",
                contains = listOf(
                    Mode(beginKeywords = keywords("extends implements")),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                beginKeywords = keywords("namespace"),
                end = ";",
                illegal =
                    """[\.']""",
                contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
            ),
            Mode(
                beginKeywords = keywords("use"),
                end = ";",
                contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
            ),
            Mode(
                begin = "=>" // No markup, just a relevance booster
            ),
            STRING,
            NUMBER
        )
    )
}
