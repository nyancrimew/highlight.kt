package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Stylus
Author = Bryant Williams <b.n.williams@gmail.com>
Description = Stylus (https = //github.com/LearnBoost/stylus/)
Category = css
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/stylus.js MD5 <48f6fdaf3175f31def6b3e6f550d0940>
 */
internal fun stylus(): Mode {
    var VARIABLE = Mode(
        className = "variable",
        begin = "\\\$" +
            hljs.IDENT_RE
    )
    var HEX_COLOR = Mode(
        className = "number",
        begin = "#([a-fA-F0-9]{6}|[a-fA-F0-9]{3))"
    )
    var AT_KEYWORDS = listOf(
        "charset",
        "css",
        "debug",
        "extend",
        "font-face",
        "for",
        "import",
        "include",
        "media",
        "mixin",
        "page",
        "warn",
        "while"
    )
    var PSEUDO_SELECTORS = listOf(
        "after",
        "before",
        "first-letter",
        "first-line",
        "active",
        "first-child",
        "focus",
        "hover",
        "lang",
        "link",
        "visited"
    )
    var TAGS = listOf(
        "a",
        "abbr",
        "address",
        "article",
        "aside",
        "audio",
        "b",
        "blockquote",
        "body",
        "button",
        "canvas",
        "caption",
        "cite",
        "code",
        "dd",
        "del",
        "details",
        "dfn",
        "div",
        "dl",
        "dt",
        "em",
        "fieldset",
        "figcaption",
        "figure",
        "footer",
        "form",
        "h1",
        "h2",
        "h3",
        "h4",
        "h5",
        "h6",
        "header",
        "hgroup",
        "html",
        "i",
        "iframe",
        "img",
        "input",
        "ins",
        "kbd",
        "label",
        "legend",
        "li",
        "mark",
        "menu",
        "nav",
        "object",
        "ol",
        "p",
        "q",
        "quote",
        "samp",
        "section",
        "span",
        "strong",
        "summary",
        "sup",
        "table",
        "tbody",
        "td",
        "textarea",
        "tfoot",
        "th",
        "thead",
        "time",
        "tr",
        "ul",
        "var",
        "video"
    )
    var TAG_END = "[\\.\\s\\n\\[\:,]"
    var ATTRIBUTES = listOf(
        "align-content",
        "align-items",
        "align-self",
        "animation",
        "animation-delay",
        "animation-direction",
        "animation-duration",
        "animation-fill-mode",
        "animation-iteration-count",
        "animation-name",
        "animation-play-state",
        "animation-timing-function",
        "auto",
        "backface-visibility",
        "background",
        "background-attachment",
        "background-clip",
        "background-color",
        "background-image",
        "background-origin",
        "background-position",
        "background-repeat",
        "background-size",
        "border",
        "border-bottom",
        "border-bottom-color",
        "border-bottom-left-radius",
        "border-bottom-right-radius",
        "border-bottom-style",
        "border-bottom-width",
        "border-collapse",
        "border-color",
        "border-image",
        "border-image-outset",
        "border-image-repeat",
        "border-image-slice",
        "border-image-source",
        "border-image-width",
        "border-left",
        "border-left-color",
        "border-left-style",
        "border-left-width",
        "border-radius",
        "border-right",
        "border-right-color",
        "border-right-style",
        "border-right-width",
        "border-spacing",
        "border-style",
        "border-top",
        "border-top-color",
        "border-top-left-radius",
        "border-top-right-radius",
        "border-top-style",
        "border-top-width",
        "border-width",
        "bottom",
        "box-decoration-break",
        "box-shadow",
        "box-sizing",
        "break-after",
        "break-before",
        "break-inside",
        "caption-side",
        "clear",
        "clip",
        "clip-path",
        "color",
        "column-count",
        "column-fill",
        "column-gap",
        "column-rule",
        "column-rule-color",
        "column-rule-style",
        "column-rule-width",
        "column-span",
        "column-width",
        "columns",
        "content",
        "counter-increment",
        "counter-reset",
        "cursor",
        "direction",
        "display",
        "empty-cells",
        "filter",
        "flex",
        "flex-basis",
        "flex-direction",
        "flex-flow",
        "flex-grow",
        "flex-shrink",
        "flex-wrap",
        "float",
        "font",
        "font-family",
        "font-feature-settings",
        "font-kerning",
        "font-language-override",
        "font-size",
        "font-size-adjust",
        "font-stretch",
        "font-style",
        "font-variant",
        "font-variant-ligatures",
        "font-weight",
        "height",
        "hyphens",
        "icon",
        "image-orientation",
        "image-rendering",
        "image-resolution",
        "ime-mode",
        "inherit",
        "initial",
        "justify-content",
        "left",
        "letter-spacing",
        "line-height",
        "list-style",
        "list-style-image",
        "list-style-position",
        "list-style-type",
        "margin",
        "margin-bottom",
        "margin-left",
        "margin-right",
        "margin-top",
        "marks",
        "mask",
        "max-height",
        "max-width",
        "min-height",
        "min-width",
        "nav-down",
        "nav-index",
        "nav-left",
        "nav-right",
        "nav-up",
        "none",
        "normal",
        "object-fit",
        "object-position",
        "opacity",
        "order",
        "orphans",
        "outline",
        "outline-color",
        "outline-offset",
        "outline-style",
        "outline-width",
        "overflow",
        "overflow-wrap",
        "overflow-x",
        "overflow-y",
        "padding",
        "padding-bottom",
        "padding-left",
        "padding-right",
        "padding-top",
        "page-break-after",
        "page-break-before",
        "page-break-inside",
        "perspective",
        "perspective-origin",
        "pointer-events",
        "position",
        "quotes",
        "resize",
        "right",
        "tab-size",
        "table-layout",
        "text-align",
        "text-align-last",
        "text-decoration",
        "text-decoration-color",
        "text-decoration-line",
        "text-decoration-style",
        "text-indent",
        "text-overflow",
        "text-rendering",
        "text-shadow",
        "text-transform",
        "text-underline-position",
        "top",
        "transform",
        "transform-origin",
        "transform-style",
        "transition",
        "transition-delay",
        "transition-duration",
        "transition-property",
        "transition-timing-function",
        "unicode-bidi",
        "vertical-align",
        "visibility",
        "white-space",
        "widows",
        "width",
        "word-break",
        "word-spacing",
        "word-wrap",
        "z-index"
    )
    // illegals
    var ILLEGAL = listOf(
        "\\?",
        "(\\bReturn\\b)",
        // monkey
        "(\\bEnd\\b)",
        // monkey
        "(\\bend\\b)",
        // vbscript
        "(\\bdef\\b)",
        // gradle
        ";",
        // a whole lot of languages
        "#\\s",
        // markdown
        "\\*\\s",
        // markdown
        "===\\s",
        // markdown
        "\\|",
        "%"
        // prolog
    )
    return Mode(
        aliases = listOf("styl"),
        case_insensitive = false,
        keywords = keywords("if else for in"),
        illegal = "(" +
            ILLEGAL.joinToString("|") +
            ")",
        contains = listOf(
            // strings
            hljs.QUOTE_STRING_MODE,
            hljs.APOS_STRING_MODE,
            // comments
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            // hex colors
            HEX_COLOR,
            // class tag
            Mode(
                begin = "\\.[a-zA-Z][a-zA-Z0-9_-]*" +
                    TAG_END,
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "selector-class",
                        begin = "\\.[a-zA-Z][a-zA-Z0-9_-]*"
                    )
                )
            ),
            // id tag
            Mode(
                begin = "\\#[a-zA-Z][a-zA-Z0-9_-]*" +
                    TAG_END,
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "selector-id",
                        begin = "\\#[a-zA-Z][a-zA-Z0-9_-]*"
                    )
                )
            ),
            // tags
            Mode(
                begin = "\\b(" +
                    TAGS.joinToString("|") +
                    ")" +
                    TAG_END,
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "selector-tag",
                        begin = "\\b[a-zA-Z][a-zA-Z0-9_-]*"
                    )
                )
            ),
            // psuedo selectors
            Mode(
                begin = "&?:?:\\b(" +
                    PSEUDO_SELECTORS.joinToString("|") +
                    ")" +
                    TAG_END
            ),
            // @ keywords
            Mode(
                begin = "\@(" +
                    AT_KEYWORDS.joinToString("|") +
                    ")\\b"
            ),
            // variables
            VARIABLE,
            // dimension
            hljs.CSS_NUMBER_MODE,
            // number
            hljs.NUMBER_MODE,
            // functions
            //  - only from beginning of line + whitespace
            Mode(
                className = "function",
                begin = "^[a-zA-Z][a-zA-Z0-9_\-]*\\(.*\\)",
                illegal = "[\\n]",
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "title",
                        begin = "\\b[a-zA-Z][a-zA-Z0-9_\-]*"
                    ),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        contains = listOf(
                            HEX_COLOR,
                            VARIABLE,
                            hljs.APOS_STRING_MODE,
                            hljs.CSS_NUMBER_MODE,
                            hljs.NUMBER_MODE,
                            hljs.QUOTE_STRING_MODE
                        )
                    )
                )
            ),
            // attributes
            //  - only from beginning of line + whitespace
            //  - must have whitespace after it
            Mode(
                className = "attribute",
                begin = "\\b(" +
                    ATTRIBUTES.reversed().joinToString("|") +
                    ")\\b",
                starts = Mode(
                    // value container
                    end =
                        """;|${'$'}""",
                    contains = listOf(
                        HEX_COLOR,
                        VARIABLE,
                        hljs.APOS_STRING_MODE,
                        hljs.QUOTE_STRING_MODE,
                        hljs.CSS_NUMBER_MODE,
                        hljs.NUMBER_MODE,
                        hljs.C_BLOCK_COMMENT_MODE
                    ),
                    illegal =
                        """\.""",
                    relevance = 0
                )
            )
        )
    )
}
