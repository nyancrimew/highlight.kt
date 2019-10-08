package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Q
Author = Sergey Vidyuk <svidyuk@gmail.com>
Description = K/Q/Kdb+ from Kx Systems
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class QLanguage : LanguageBuilder  {
  val Q_KEYWORDS = listOf(
  Keyword(className = "keyword",

 value = "do while select delete by update from"),
  Keyword(className = "literal",

 value = "0b 1b"),
  Keyword(className = "built_in",

 value = "neg not null string reciprocal floor ceiling signum mod xbar xlog and or each scan over prior mmu lsq inv md5 ltime gtime count first val dev med cov cor all any rand sums prds mins maxs fills deltas ratios avgs differ prev next rank reverse iasc idesc asc desc msum mcount mavg mdev xrank mmin mmax xprev rotate distinct group where flip type key til get value attr cut set upsert raze union inter except cross sv vs sublist enlist read0 read1 hopen hclose hdel hsym hcount peach system ltrim rtrim trim lower upper ssr view tables views cols xcols keys xkey xcol xasc xdesc fkeys meta lj aj aj0 ij pj asof uj ww wj wj1 fby xgroup ungroup ej save load rsave rload show csv parse eval min max avg wavg wsum sin cos tan sum"),
  Keyword(className = "type",

 value = "`float `double int `timestamp `timespan `datetime `time `boolean `symbol `char `byte `short `long `real `month `date `minute `second `guid"
  ));
  override fun build() = Mode(
  aliases:["k",
 "kdb"),
  keywords = keywords(Q_KEYWORDS),
  lexemes = """(`?)[A-Za-z0-9_]+\b""",

  contains = listOf(
  hljs.C_LINE_COMMENT_MODE,
    hljs.QUOTE_STRING_MODE,
    hljs.C_NUMBER_MODE
     )
  );
}
