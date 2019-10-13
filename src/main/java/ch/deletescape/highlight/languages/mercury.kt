package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.*
import ch.deletescape.highlight.core.hljs

/*
Language = Mercury
Author = mucaho <mkucko@gmail.com>
Description = Mercury is a logic/functional programming language which combines the clarity and expressiveness of declarative programming with advanced static analysis and error detection features.
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/mercury.js MD5 <f63f64e46a56b69e35b0966301e7d214>
 */
internal fun mercury(): Mode {
    var KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "module use_module import_module include_module end_module initialise mutable initialize finalize finalise interface implementation pred mode func type inst solver any_pred any_func is semidet det nondet multi erroneous failure cc_nondet cc_multi typeclass instance where pragma promise external trace atomic or_else require_complete_switch require_det require_semidet require_multi require_nondet require_cc_multi require_cc_nondet require_erroneous require_failure"
        ),
        keyword(
            className = "meta",
            value =
                // pragma
                "inline no_inline type_spec source_file fact_table obsolete memo loop_check minimal_model terminates does_not_terminate check_termination promise_equivalent_clauses foreign_proc foreign_decl foreign_code foreign_type foreign_import_module foreign_export_enum foreign_export foreign_enum may_call_mercury will_not_call_mercury thread_safe not_thread_safe maybe_thread_safe promise_pure promise_semipure tabled_for_io local untrailed trailed attach_to_io_state can_pass_as_mercury_type stable will_not_throw_exception may_modify_trail will_not_modify_trail may_duplicate may_not_duplicate affects_liveness does_not_affect_liveness doesnt_affect_liveness no_sharing unknown_sharing sharing"
        ),
        keyword(
            className = "built_in",
            value =
                "some all not if then else true fail false try catch catch_any semidet_true semidet_false semidet_fail impure_true impure semipure"
        )
    ).flatten()
    var COMMENT = hljs.COMMENT(
        "%",
        "$"
    )
    var NUMCODE = Mode(
        className = "number",
        begin = "0'.\\|0[box][0-9a-fA-F]*"
    )
    var ATOM = hljs.inherit(hljs.APOS_STRING_MODE, Mode(relevance = 0))
    var STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(relevance = 0))
    var STRING_FMT = Mode(
        className = "subst",
        begin = "\\\\[abfnrtv]\\|\\\\x[0-9a-fA-F]*\\\\\\|%[-+# *.0-9]*[dioxXucsfeEgGp]",
        relevance = 0
    )
    STRING.contains += listOf(STRING_FMT)
    var IMPLICATION = Mode(
        className = "built_in",
        variants = listOf(
            Mode(begin = "<=>"),
            Mode(
                begin = "<=",
                relevance = 0
            ),
            Mode(
                begin = "=>",
                relevance = 0
            ),
            Mode(begin = "/\\\\"),
            Mode(begin = "\\\\/")
        )
    )
    var HEAD_BODY_CONJUNCTION = Mode(
        className = "built_in",
        variants = listOf(
            Mode(begin = ":-\\|-->"),
            Mode(
                begin = "=",
                relevance = 0
            )
        )
    )
    return Mode(
        aliases = listOf(
            "m",
            "moo"
        ),
        keywords = keywords(KEYWORDS),
        contains = listOf(
            IMPLICATION,
            HEAD_BODY_CONJUNCTION,
            COMMENT,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMCODE,
            hljs.NUMBER_MODE,
            ATOM,
            STRING,
            Mode(
                begin =
                    """:-"""
            ) // relevance booster
        )
    )
}
