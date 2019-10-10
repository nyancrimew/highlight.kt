package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Smali
Author = Dennis Titze <dennis.titze@gmail.com>
Description = Basic Smali highlighting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/smali.js MD5 <7ddf2d188885b364ff35f7b7a501cdad>
 */
internal fun smali(): Mode {
    var smali_instr_low_prio = listOf(
        "add",
        "and",
        "cmp",
        "cmpg",
        "cmpl",
        "const",
        "div",
        "double",
        "float",
        "goto",
        "if",
        "int",
        "long",
        "move",
        "mul",
        "neg",
        "new",
        "nop",
        "not",
        "or",
        "rem",
        "return",
        "shl",
        "shr",
        "sput",
        "sub",
        "throw",
        "ushr",
        "xor"
    )
    var smali_instr_high_prio = listOf(
        "aget",
        "aput",
        "array",
        "check",
        "execute",
        "fill",
        "filled",
        "goto/16",
        "goto/32",
        "iget",
        "instance",
        "invoke",
        "iput",
        "monitor",
        "packed",
        "sget",
        "sparse"
    )
    var smali_keywords = keywords(
        listOf("transient"),
        "constructor",
        "abstract",
        "final",
        "synthetic",
        "public",
        "private",
        "protected",
        "static",
        "bridge",
        "system"
    )
    return Mode(
        aliases = listOf("smali"),
        contains = listOf(
            Mode(
                className = "string",
                begin = "\"",
                end = "\"",
                relevance = 0
            ),
            hljs.COMMENT(
                "#",
                "\$",
                Mode(
                    relevance = 0
                )
            ),
            Mode(
                className = "keyword",
                variants = listOf(
                    Mode(begin = "\\s*\\.end\\s[a-zA-Z0-9]*"),
                    Mode(
                        begin = "^[ ]*\\.[a-zA-Z]*",
                        relevance = 0
                    ),
                    Mode(
                        begin = "\\s = [a-zA-Z_0-9]*",
                        relevance = 0
                    ),
                    Mode(
                        begin = "\\s(" +
                            smali_keywords.joinToString("|") +
                            ")"
                    )
                )
            ),
            Mode(
                className = "built_in",
                variants = listOf(
                    Mode(
                        begin = "\\s('+smali_instr_low_prio.joinToString('|')+')\\s"
                    ),
                    Mode(
                        begin = "\\s('+smali_instr_low_prio.joinToString('|')+')((\\-|/)[a-zA-Z0-9]+)+\\s",
                        relevance = 10
                    ),
                    Mode(
                        begin = "\\s('+smali_instr_high_prio.joinToString('|')+')((\\-|/)[a-zA-Z0-9]+)*\\s",
                        relevance = 10
                    )
                )
            ),
            Mode(
                className = "class",
                begin = "L[^\(;:\n]*;",
                relevance = 0
            ),
            Mode(
                begin = "[vp][0-9]+"
            )
        )
    )
}
