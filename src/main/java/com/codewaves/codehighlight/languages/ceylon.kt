package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Ceylon
Author = Lucas Werkmeister <mail@lucaswerkmeister.de>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/ceylon.js MD5 <436e1781b0c28b573d94547a23d3ac7d>
 */
internal fun ceylon(): Mode {
    // 2.3. Identifiers and keywords
    var KEYWORDS =
        "assembly module package import alias class interface object given value assign void function new of extends satisfies abstracts in out return break continue throw assert dynamic if else switch case for while try catch finally then let this outer super is exists nonempty"
    // 7.4.1 Declaration Modifiers
    var DECLARATION_MODIFIERS =
        "shared abstract formal default actual variable late native deprecatedfinal sealed annotation suppressWarnings small"
    // 7.4.2 Documentation
    var DOCUMENTATION =
        "doc by license see throws tagged"
    var SUBST = Mode(
        className = "subst",
        excludeBegin = true,
        excludeEnd = true,
        begin =
            """``""",
        end =
            """``""",
        keywords = keywords(KEYWORDS),
        relevance = 10
    )
    var EXPRESSIONS = listOf(
        Mode(
            // verbatim string
            className = "string",
            begin = "\"\"\"",
            end = "\"\"\"",
            relevance = 10
        ),
        Mode(
            // string literal or template
            className = "string",
            begin = "\"",
            end = "\"",
            contains = listOf(SUBST)
        ),
        Mode(
            // character literal
            className = "string",
            begin = "'",
            end = "'"
        ),
        Mode(
            // numeric literal
            className = "number",
            begin = "#[0-9a-fA-F_]+|\\\$[01_]+|[0-9_]+(?:\\.[0-9_](?:[eE][+-]?\\d+)?)?[kMGTPmunpf]?",
            relevance = 0
        )
    )
    SUBST.contains = EXPRESSIONS
    return Mode(
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = KEYWORDS + " " +
                    DECLARATION_MODIFIERS
            ),
            Keyword(
                className = "meta",
                value = DOCUMENTATION
            )
        ),
        illegal = "\\\$[^01]|#[^0-9a-fA-F]",
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.COMMENT(
                "/\\*",
                "\\*/",
                Mode(contains = listOf(hljs.SELF))
            ),
            Mode(
                // compiler annotation
                className = "meta",
                begin = "@[a-z]\\w*(?:\:\"[^\"]*\")?"
            )
        ) + EXPRESSIONS
    )
}
