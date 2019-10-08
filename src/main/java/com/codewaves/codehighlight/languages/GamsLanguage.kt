package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*


/*
 Language = GAMS
 Author = Stefan Bechert <stefan.bechert@gmx.net>
 Contributors = Oleg Efimov <efimovov@gmail.com>, Mikko Kouhia <mikko.kouhia@iki.fi>
 Description = The General Algebraic Modeling System language
 Category = scientific
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class GamsLanguage : LanguageBuilder  {
  val KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "abort acronym acronyms alias all and assign binary card diag display else eq file files for free ge gt if integer le loop lt maximizing minimizing model models ne negative no not option options or ord positive prod put putpage puttl repeat sameas semicont semiint smax smin solve sos1 sos2 sum system table then until using while xor yes"),
    Keyword(className = "literal",

 value = "eps inf na"),
    Keyword(className = "built-in",

 value = "abs arccos arcsin arctan arctan2 Beta betaReg binomial ceil centropy cos cosh cvPower div div0 eDist entropy errorf execSeed exp fact floor frac gamma gammaReg log logBeta logGamma log10 log2 mapVal max min mod ncpCM ncpF ncpVUpow ncpVUsin normal pi poly power randBinomial randLinear randTriangle round rPower sigmoid sign signPower sin sinh slexp sllog10 slrec sqexp sqlog10 sqr sqrec sqrt tan tanh trunc uniform uniformInt vcPower bool_and bool_eqv bool_imp bool_not bool_or bool_xor ifThen rel_eq rel_ge rel_gt rel_le rel_lt rel_ne gday gdow ghour gleap gmillisec gminute gmonth gsecond gyear jdate jnow jstart jtime errorLevel execError gamsRelease gamsVersion handleCollect handleDelete handleStatus handleSubmit heapFree heapLimit heapSize jobHandle jobKill jobStatus jobTerminate licenseLevel licenseStatus maxExecError sleep timeClose timeComp timeElapsed timeExec timeStart"
  ));
  val PARAMS = Mode(
    className = "params",

    begin = """\(""",

 end = """\)""",

    excludeBegin = true,
    excludeEnd = true
  );
  val SYMBOLS = Mode(
    className = "symbol",

    variants = listOf(
      Mode(begin = """\=[lgenxc]="""),
      Mode(begin = """\${'$'}""")
    )
  );
  val QSTR = Mode( // One-line quoted comment string
    className = "comment",

    variants = listOf(
      Mode(begin = "\'",

 end = "\'"),
      Mode(begin = "\"",

 end = "\"")
    ),
    illegal = "\\n",

    contains = listOf(hljs.BACKSLASH_ESCAPE)
  );
  val ASSIGNMENT = Mode(
    begin = "/",

    end = "/",

    keywords = keywords(KEYWORDS),
    contains = listOf(
      QSTR,
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      hljs.QUOTE_STRING_MODE,
      hljs.APOS_STRING_MODE,
      hljs.C_NUMBER_MODE
    )
  );
  val DESCTEXT = Mode( // Parameter/set/variable description text
    begin = """[a-z][a-z0-9_]*(\(listOf(a-z0-9_, ]*\))?[ \t]+""",

    excludeBegin = true,
    end = "\$",

    endsWithParent = true,
    contains = listOf(
      QSTR,
      ASSIGNMENT,
      Mode(
        className = "comment",

        begin = """(listOf( ]*[a-z0-9&#*=?@>\\<:\-,()${'$'}\[\]_.{}!+%^]+)+""",

        relevance = 0
      )
    )
  );

  override fun build() = Mode(
    aliases = listOf("gms"),
    case_insensitive = true,
    keywords = keywords(KEYWORDS),
    contains = listOf(
      hljs.COMMENT("""^\${'$'}ontext""",
 """^\${'$'}offtext"""),
      Mode(
        className = "meta",

        begin = "^\\$[a-z0-9]+",

        end = "\$",

        returnBegin = true,
        contains = listOf(
          Mode(
            className = "meta-keyword",

            begin = "^\\$[a-z0-9]+"

          )
        )
      ),
      hljs.COMMENT("^\\*",
 "\$"),
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      hljs.QUOTE_STRING_MODE,
      hljs.APOS_STRING_MODE,
      // Declarations
      Mode(
        beginKeywords =           keywords("set sets parameter parameters variable variables scalar scalars equation equations"),
        end = ";",

        contains = listOf(
          hljs.COMMENT("^\\*",
 "\$"),
          hljs.C_LINE_COMMENT_MODE,
          hljs.C_BLOCK_COMMENT_MODE,
          hljs.QUOTE_STRING_MODE,
          hljs.APOS_STRING_MODE,
          ASSIGNMENT,
          DESCTEXT
        )
      ),
      Mode( // table environment
        beginKeywords = keywords("table"),
        end = ";",

        returnBegin = true,
        contains = listOf(
          Mode( // table header row
            beginKeywords = keywords("table"),
            end = "\$",

            contains = listOf(DESCTEXT)
          ),
          hljs.COMMENT("^\\*",
 "\$"),
          hljs.C_LINE_COMMENT_MODE,
          hljs.C_BLOCK_COMMENT_MODE,
          hljs.QUOTE_STRING_MODE,
          hljs.APOS_STRING_MODE,
          hljs.C_NUMBER_MODE
          // Table does not contain DESCTEXT or ASSIGNMENT
        )
      ),
      // Function definitions
      Mode(
        className = "function",

        begin = """^[a-z][a-z0-9_,\-+" ()\$]+\.{2}\"\"",

        returnBegin = true,
        contains = listOf(
              Mode( // Function title
                className = "title",

                begin = """^[a-z0-9_]+"""

              ),
              PARAMS,
              SYMBOLS
            )
      ),
      hljs.C_NUMBER_MODE,
      SYMBOLS
    )
  );
}
