package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = HTML, XML
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/xml.js MD5 <8d1fe692045603803f24f0f60fd6f450>
 */
internal fun xml(): Mode {
    var XML_IDENT_RE = "[A-Za-z0-9\\._ = -]+"
    var TAG_INTERNALS = Mode(
        endsWithParent = true,
        illegal =
            """<""",
        relevance = 0,
        contains = listOf(
            Mode(
                className = "attr",
                begin = XML_IDENT_RE,
                relevance = 0
            ),
            Mode(
                begin =
                    """=\s*""",
                relevance = 0,
                contains = listOf(
                    Mode(
                        className = "string",
                        endsParent = true,
                        variants = listOf(
                            Mode(
                                begin =
                                    """"""",
                                end =
                                    """""""
                            ),
                            Mode(
                                begin =
                                    """'""",
                                end =
                                    """'"""
                            ),
                            Mode(
                                begin =
                                    """[^\s"'=<>`]+"""
                            )
                        )
                    )
                )
            )
        )
    )
    return Mode(
        aliases = listOf(
            "html",
            "xhtml",
            "rss",
            "atom",
            "xjb",
            "xsd",
            "xsl",
            "plist",
            "wsf",
            "svg"
        ),
        case_insensitive = true,
        contains = listOf(
            Mode(
                className = "meta",
                begin = "<!DOCTYPE",
                end = ">",
                relevance = 10,
                contains = listOf(
                    Mode(
                        begin = "\\[",
                        end = "\\]"
                    )
                )
            ),
            hljs.COMMENT(
                "<!--",
                "-->",
                Mode(
                    relevance = 10
                )
            ),
            Mode(
                begin = "<\\!\\[CDATA\\[",
                end = "\\]\\]>",
                relevance = 10
            ),
            Mode(
                className = "meta",
                begin =
                    """<\?xml""",
                end =
                    """\?>""",
                relevance = 10
            ),
            Mode(
                begin =
                    """<\?(php)?""",
                end =
                    """\?>""",
                subLanguage = "php",
                contains = listOf(
                    // We don't want the php closing tag ?> to close the PHP block when
                    // inside any of the following blocks:
                    Mode(
                        begin = "/\\*",
                        end = "\\*/",
                        skip = true
                    ),
                    Mode(
                        begin = "b\"",
                        end = "\"",
                        skip = true
                    ),
                    Mode(
                        begin = "b'",
                        end = "'",
                        skip = true
                    ),
                    hljs.inherit(
                        hljs.APOS_STRING_MODE,
                        Mode(
                            illegal = null,
                            className = null,
                            contains = listOf(),
                            skip = true
                        )
                    ),
                    hljs.inherit(
                        hljs.QUOTE_STRING_MODE,
                        Mode(
                            illegal = null,
                            className = null,
                            contains = listOf(),
                            skip = true
                        )
                    )
                )
            ),
            Mode(
                className = "tag",
        /*
        The lookahead pattern (?=...) ensures that "begin" only matches
        "<style" as a single word, followed by a whitespace or an
        ending braket. The "\${'$'}" is needed for the lexeme to be recognized
        by hljs.subMode() that tests lexemes outside the stream.
        */
                begin = "<style(?=\\s|>|${'$'})",
                end = ">",
                keywords = listOf(
                    Keyword(
                        className = "name",
                        value = "style"
                    )
                ),
                contains = listOf(TAG_INTERNALS),
                starts = Mode(
                    end = "</style>",
                    returnEnd = true,
                    subLanguages = listOf(
                        "css",
                        "xml"
                    )
                )
            ),
            Mode(
                className = "tag",
                // See the comment in the <style tag about the lookahead pattern
                begin = "<script(?=\\s|>|${'$'})",
                end = ">",
                keywords = listOf(
                    Keyword(
                        className = "name",
                        value = "script"
                    )
                ),
                contains = listOf(TAG_INTERNALS),
                starts = Mode(
                    end = "\\<\\/script\\>",
                    returnEnd = true,
                    subLanguages = listOf(
                        "actionscript",
                        "javascript",
                        "handlebars",
                        "xml"
                    )
                )
            ),
            Mode(
                className = "tag",
                begin = "</?",
                end = "/?>",
                contains = listOf(
                    Mode(
                        className = "name",
                        begin =
                            """[^\/><\s]+""",
                        relevance = 0
                    ),
                    TAG_INTERNALS
                )
            )
        )
    )
}
