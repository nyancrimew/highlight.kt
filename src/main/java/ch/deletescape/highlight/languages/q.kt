package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Q
Author = Sergey Vidyuk <svidyuk@gmail.com>
Description = K/Q/Kdb+ from Kx Systems
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/q.js MD5 <c4f6b42ed9cdc349a633b75f53864fee>
 */
internal fun q(): Mode {
    var Q_KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "do while select delete by update from"
        ),
        keyword(
            className = "literal",
            value =
                "0b 1b"
        ),
        keyword(
            className = "built_in",
            value =
                "neg not null string reciprocal floor ceiling signum mod xbar xlog and or each scan over prior mmu lsq inv md5 ltime gtime count first var dev med cov cor all any rand sums prds mins maxs fills deltas ratios avgs differ prev next rank reverse iasc idesc asc desc msum mcount mavg mdev xrank mmin mmax xprev rotate distinct group where flip type key til get value attr cut set upsert raze union inter except cross sv vs sublist enlist read0 read1 hopen hclose hdel hsym hcount peach system ltrim rtrim trim lower upper ssr view tables views cols xcols keys xkey xcol xasc xdesc fkeys meta lj aj aj0 ij pj asof uj ww wj wj1 fby xgroup ungroup ej save load rsave rload show csv parse eval min max avg wavg wsum sin cos tan sum"
        ),
        keyword(
            className = "type",
            value =
                "`float `double int `timestamp `timespan `datetime `time `boolean `symbol `char `byte `short `long `real `month `date `minute `second `guid"
        )
    ).flatten()
    return Mode(
        aliases = listOf(
            "k",
            "kdb"
        ),
        keywords = Q_KEYWORDS,
        lexemes =
            """(`?)[A-Za-z0-9_]+\b""",
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.C_NUMBER_MODE
        )
    )
}
