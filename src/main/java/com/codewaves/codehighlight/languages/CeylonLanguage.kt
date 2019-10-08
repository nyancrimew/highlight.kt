package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.hljs
import com.codewaves.codehighlight.core.keywords

/*
Language = Ceylon
Author = Lucas Werkmeister <mail@lucaswerkmeister.de>
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CeylonLanguage : LanguageBuilder {
    // 2.3. Identifiers and keywords
    val KEYWORDS =
        "assembly module package import alias class interface object given value assign void function new of extends satisfies abstracts in out return break continue throw assert dynamic if else switch case for while try catch finally then let this outer super is exists nonempty"
    // 7.4.1 Declaration Modifiers
    val DECLARATION_MODIFIERS =
        "shared abstract formal default actual variable late native deprecatedfinal sealed annotation suppressWarnings small"
    // 7.4.2 Documentation
    val DOCUMENTATION = "doc by license see throws tagged"
    val SUBST = Mode(
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
    val EXPRESSIONS = listOf(
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
            begin = "#[0-9a-fA-F_]+|\\${'$'}[01_]+|[0-9_]+(?:\\.[0-9_](?:[eE][+-]?\\d+)?)?[kMGTPmunpf]?",
            relevance = 0
        )
    )

    override fun build() = Mode(
        keywords = keywords(KEYWORDS + " \\${'$'}[^01]|#[^0-9a-fA-F]"),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.COMMENT("/\\*", "\\*/"),
            Mode(
                // compiler annotation
                className = "meta",
                begin = "@[a-z]\\w*(?:\\:\"[^\"]*\")?"
            )
        ) + EXPRESSIONS
    )
}
