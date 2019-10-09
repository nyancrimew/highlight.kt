package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = AspectJ
Author = Hakan Ozler <ozler.hakan@gmail.com>
Description = Syntax Highlighting for the AspectJ Language which is a general-purpose aspect-oriented extension to the Java programming language.
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/aspectj.js MD5 <009349d41eeb48614b6dfe37af878400>
 */
internal fun aspectj(): Mode {
    var KEYWORDS =
        "false synchronized int abstract float private char boolean static null if const for true while long throw strictfp finally protected import native final return void enum else extends implements break transient new catch instanceof byte super volatile case assert short package default double public try this switch continue throws privileged aspectOf adviceexecution proceed cflowbelow cflow initialization preinitialization staticinitialization withincode target within execution getWithinTypeName handler thisJoinPoint thisJoinPointStaticPart thisEnclosingJoinPointStaticPart declare parents warning error soft precedence thisAspectInstance"
    var SHORTKEYS = "get set args call"
    return Mode(
        keywords = keywords(KEYWORDS),
        illegal =
            """<\/|#""",
        contains = listOf(
            hljs.COMMENT(
                "/\\*\\*",
                "\\*/",
                Mode(
                    relevance = 0,
                    contains = listOf(
                        Mode(
                            // eat up @'s in emails to prevent them to be recognized as doctags
                            begin =
                                """\w+@""",
                            relevance = 0
                        ),
                        Mode(
                            className = "doctag",
                            begin = "@[A-Za-z]+"
                        )
                    )
                )
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "class",
                beginKeywords = keywords("aspect"),
                end =
                    """[{;=]""",
                excludeEnd = true,
                illegal =
                    """[:;"\[\]]\""",
                contains = listOf(
                    Mode(
                        beginKeywords = keywords("extends implements pertypewithin perthis pertarget percflowbelow percflow issingleton")
                    ),
                    hljs.UNDERSCORE_TITLE_MODE,
                    Mode(
                        begin =
                            """\([^\)]*""",
                        end =
                            """[)]+""",
                        keywords = keywords(
                            KEYWORDS + " " +
                                SHORTKEYS
                        ),
                        excludeEnd = false
                    )
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end =
                    """[{;=]""",
                excludeEnd = true,
                relevance = 0,
                keywords = keywords("class interface"),
                illegal =
                    """[:"\[\]]\""",
                contains = listOf(
                    Mode(beginKeywords = keywords("extends implements")),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                // AspectJ Constructs
                beginKeywords = keywords("pointcut after before around throwing returning"),
                end =
                    """[)]""",
                excludeEnd = false,
                illegal =
                    """["\[\]]\""",
                contains = listOf(
                    Mode(
                        begin = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",
                        returnBegin = true,
                        contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
                    )
                )
            ),
            Mode(
                begin =
                    """[:]""",
                returnBegin = true,
                end =
                    """[{;]""",
                relevance = 0,
                excludeEnd = false,
                keywords = keywords(KEYWORDS),
                illegal =
                    """["\[\]]\""",
                contains = listOf(
                    Mode(
                        begin = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",
                        keywords = keywords(
                            KEYWORDS + " " +
                                SHORTKEYS
                        ),
                        relevance = 0
                    ),
                    hljs.QUOTE_STRING_MODE
                )
            ),
            Mode(
                // this prevents "new Name(...), or throw ..." from being recognized as a function definition
                beginKeywords = keywords("new throw"),
                relevance = 0
            ),
            Mode(
                // the function class is a bit different for AspectJ compared to the Java language
                className = "function",
                begin =
                    """\w+ +\w+(\.)?\w+\s*\([^\)]*\)\s*((throws)[\w\s,]+)?[\{;]""",
                returnBegin = true,
                end =
                    """[{;=]""",
                keywords = keywords(KEYWORDS),
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        begin = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",
                        returnBegin = true,
                        relevance = 0,
                        contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
                    ),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        relevance = 0,
                        keywords = keywords(KEYWORDS),
                        contains = listOf(
                            hljs.APOS_STRING_MODE,
                            hljs.QUOTE_STRING_MODE,
                            hljs.C_NUMBER_MODE,
                            hljs.C_BLOCK_COMMENT_MODE
                        )
                    ),
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE
                )
            ),
            hljs.C_NUMBER_MODE,
            Mode(
                // annotation is also used in this language
                className = "meta",
                begin = "@[A-Za-z]+"
            )
        )
    )
}
