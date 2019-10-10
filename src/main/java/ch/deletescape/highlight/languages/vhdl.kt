package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = VHDL
Author = Igor Kalnitsky <igor@kalnitsky.org>
Contributors = Daniel C.K. Kho <daniel.kho@tauhop.com>, Guillaume Savaton <guillaume.savaton@eseo.fr>
Description = VHDL is a hardware description language used in electronic design automation to describe digital and mixed-signal systems.
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/vhdl.js MD5 <66556b070868ad69b757448c82b7cb77>
 */
internal fun vhdl(): Mode {
    // Regular expression for VHDL numeric literals.

    // Decimal literal:
    var INTEGER_RE = "\\d(_|\\d)*"
    var EXPONENT_RE = "[eE][-+]?" +
        INTEGER_RE
    var DECIMAL_LITERAL_RE = INTEGER_RE + "(\\." +
        INTEGER_RE + ")?(" +
        EXPONENT_RE + ")?"
    // Based literal:
    var BASED_INTEGER_RE = "\\w+"
    var BASED_LITERAL_RE = INTEGER_RE + "#" +
        BASED_INTEGER_RE + "(\\." +
        BASED_INTEGER_RE + ")?#(" +
        EXPONENT_RE + ")?"
    var NUMBER_RE = "\\b(" +
        BASED_LITERAL_RE + "|" +
        DECIMAL_LITERAL_RE + ")"
    return Mode(
        case_insensitive = true,
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    "abs access after alias all and architecture array assert assume assume_guarantee attribute begin block body buffer bus case component configuration constant context cover disconnect downto default else elsif end entity exit fairness file for force function generate generic group guarded if impure in inertial inout is label library linkage literal loop map mod nand new next nor not null of on open or others out package parameter port postponed procedure process property protected pure range record register reject release rem report restrict restrict_guarantee return rol ror select sequence severity shared signal sla sll sra srl strong subtype then to transport type unaffected units until use variable view vmode vprop vunit wait when while with xnor xor"
            ),
            Keyword(
                className = "built_in",
                value =
                    "boolean bit character integer time delay_length natural positive string bit_vector file_open_kind file_open_status std_logic std_logic_vector unsigned signed boolean_vector integer_vector std_ulogic std_ulogic_vector unresolved_unsigned u_unsigned unresolved_signed u_signed real_vector time_vector"
            ),
            Keyword(
                className = "literal",
                value =
                    "false true note warning error failure line text side width" // textio
            )
        ),
        illegal = "{",
        contains = listOf(
            hljs.C_BLOCK_COMMENT_MODE, // VHDL-2008 block commenting.
            hljs.COMMENT(
                "--",
                "\$"
            ),
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "number",
                begin = NUMBER_RE,
                relevance = 0
            ),
            Mode(
                className = "string",
                begin = "'(U|X|0|1|Z|W|L|H|-)'",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                className = "symbol",
                begin = "'[A-Za-z](_?[A-Za-z0-9))*",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            )
        )
    )
}
