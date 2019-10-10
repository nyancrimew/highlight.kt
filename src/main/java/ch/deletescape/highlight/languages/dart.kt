package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Dart
Requires = markdown.js
Author = Maxim Dikun <dikmax@gmail.com>
Description = Dart a modern, object-oriented language developed by Google. For more information see https = //www.dartlang.org/
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/dart.js MD5 <018cfe8db327f0274122a9f3c0041e01>
 */
internal fun dart(): Mode {
    var SUBST = Mode(
        className = "subst",
        variants = listOf(
            Mode(
                begin = "\\\$[A-Za-z0-9_]+"
            )
        )
    )
    var BRACED_SUBST = Mode(
        className = "subst",
        variants = listOf(
            Mode(
                begin = "\\\${",
                end = "}"
            )
        ),
        keywords = keywords("true false null this is new super")
    )
    var STRING = Mode(
        className = "string",
        variants = listOf(
            Mode(
                begin = "r'''",
                end = "'''"
            ),
            Mode(
                begin = "r\"\"\"",
                end = "\"\"\""
            ),
            Mode(
                begin = "r'",
                end = "'",
                illegal = "\\n"
            ),
            Mode(
                begin = "r\"",
                end = "\"",
                illegal = "\\n"
            ),
            Mode(
                begin = "'''",
                end = "'''",
                contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, BRACED_SUBST)
            ),
            Mode(
                begin = "\"\"\"",
                end = "\"\"\"",
                contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, BRACED_SUBST)
            ),
            Mode(
                begin = "'",
                end = "'",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, BRACED_SUBST)
            ),
            Mode(
                begin = "\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, BRACED_SUBST)
            )
        )
    )
    BRACED_SUBST.contains = listOf(
        hljs.C_NUMBER_MODE, STRING
    )
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value = "abstract as assert async await break case catch class const continue covariant default deferred do dynamic else enum export extends extension external factory false final finally for Function get hide if implements import in inferface is library mixin new null on operator part rethrow return set show static super switch sync this throw true try typedef var void while with yield"
        ),
        Keyword(
            className = "built_in",
            value =
                // dart:core
                "Comparable DateTime Duration Function Iterable Iterator List Map Match Null Object Pattern RegExp Set Stopwatch String StringBuffer StringSink Symbol Type Uri bool double dynamic int num print Element ElementList document querySelector querySelectorAll window"
        )
    )
    return Mode(
        keywords = keywords(KEYWORDS),
        contains = listOf(
            STRING,
            hljs.COMMENT(
                "/\\*\\*",
                "\\*/",
                Mode(
                    subLanguage = "markdown"
                )
            ),
            hljs.COMMENT(
                "///+\\s*",
                "\$",
                Mode(
                    contains = listOf(
                        Mode(
                            subLanguage = "markdown",
                            begin = ".",
                            end = "\$"
                        )
                    )
                )
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end = "{",
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends implements")
                    ),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",
                begin = "@[A-Za-z]+"
            ),
            Mode(
                begin = "=>" // No markup, just a relevance booster
            )
        )
    )
}
