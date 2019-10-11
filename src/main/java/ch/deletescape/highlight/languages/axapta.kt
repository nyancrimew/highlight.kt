package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Axapta
Author = Dmitri Roudakov <dmitri@roudakov.ru>
Category = enterprise
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/axapta.js MD5 <57e5a48bd5c8e39ea6aacc5290dcab62>
 */
internal fun axapta(): Mode {
    return Mode(
        keywords = keywords("false int abstract private char boolean static null if for true while long throw finally protected final return void enum else break new catch byte super case short default double public try this switch continue reverse firstfast firstonly forupdate nofetch sum avg minof maxof count order group by asc desc index hint like dispaly edit client server ttsbegin ttscommit str real date container anytype common div mod"),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE,
            Mode(
                className = "meta",
                begin = "#",
                end = "\$"
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class interface"),
                end = "\\{",
                excludeEnd = true,
                illegal = ":",
                contains = listOf(
                    Mode(beginKeywords = keywords("extends implements")),
                    hljs.UNDERSCORE_TITLE_MODE
                )
            )
        )
    )
}
