package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Lasso
Author = Eric Knibbe <eric@lassosoft.com>
Description = Lasso is a language and server platform for database-driven web applications. This definition handles Lasso 9 syntax and LassoScript for Lasso 8.6 and earlier.
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LassoLanguage : LanguageBuilder  {
  val LASSO_IDENT_RE = "[a-zA-Z_][\\w.]*";
  val LASSO_ANGLE_RE = "<\\?(lasso(script)?|=)";
  val LASSO_CLOSE_RE = "\\]|\\?>";
  val LASSO_KEYWORDS = listOf(
    Keyword(className = "literal",

 value = "true false none minimal full all void and or not bw nbw ew new cn ncn lt lte gt gte eq neq rx nrx ft"),
    Keyword(className = "built_in",

 value = "array date decimal duration integer map pair string tag xml null boolean bytes keyword list locale queue set stack staticarray local val variable global data self inherited currentcapture givenblock"),
    Keyword(className = "keyword",

 value = "cache database_names database_schemanames database_tablenames define_tag define_type email_batch encode_set html_comment handle handle_error header if inline iterate ljax_target link link_currentaction link_currentgroup link_currentrecord link_detail link_firstgroup link_firstrecord link_lastgroup link_lastrecord link_nextgroup link_nextrecord link_prevgroup link_prevrecord log loop namespace_using output_none portal private protect records referer referrer repeating resultset rows search_args search_arguments select sort_args sort_arguments thread_atomic value_list while abort case else fail_if fail_ifnot fail if_empty if_false if_null if_true loop_abort loop_continue loop_count params params_up return return_value run_children soap_definetag soap_lastrequest soap_lastresponse tag_name ascending average by define descending do equals frozen group handle_failure import in into join let match max min on order parent protected provide public require returnhome skip split_thread sum take thread to trait type where with yield yieldhome"
  ));
  val HTML_COMMENT = hljs.COMMENT(
    "<!--",

    "-->",

    Mode(
      relevance = 0
    )
  );
  val LASSO_NOPROCESS = Mode(
    className = "meta",

    begin = "\\[noprocess\\]",

    starts = Mode(
      end = "\\[/noprocess\\]",

      returnEnd = true,
      contains = listOf(HTML_COMMENT)
    )
  );
  val LASSO_START = Mode(
    className = "meta",

    begin = "\\[/noprocess|" +
 LASSO_ANGLE_RE
  );
  val LASSO_DATAMEMBER = Mode(
    className = "symbol",

    begin = "\'" +
 LASSO_IDENT_RE + "\'"
  );
  val LASSO_CODE = listOf(
    hljs.C_LINE_COMMENT_MODE,
    hljs.C_BLOCK_COMMENT_MODE,
    hljs.inherit(hljs.C_NUMBER_MODE, Mode(begin = hljs.C_NUMBER_RE + "|(-?infinity|NaN)\\b")),
    hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
    hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null)),
    Mode(
      className = "string",

      begin = "`",

 end = "`"
    ),
    Mode( // variables
      variants = listOf(
        Mode(
          begin = "[#\$]" +
 LASSO_IDENT_RE
        ),
        Mode(
          begin = "#",

 end = "\\d+",

          illegal = "\\W"
        )
      )
    ),
    Mode(
      className = "type",

      begin = "::\\s*",

 end = LASSO_IDENT_RE,
      illegal = "\\W"
    ),
    Mode(
      className = "params",

      variants = listOf(
        Mode(
          begin = "-(?!infinity)" +
 LASSO_IDENT_RE,
          relevance = 0
        ),
        Mode(
          begin = "(\\.\\.\\.)"
        )
      )
    ),
    Mode(
      begin = """(->|\.)\s*""",

      relevance = 0,
      contains = listOf(LASSO_DATAMEMBER)
    ),
    Mode(
      className = "class",

      beginKeywords = keywords("define"),
      returnEnd = true,
 end = "\\(|=>",

      contains = listOf(
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = LASSO_IDENT_RE + "(=(?!>))?|[-+*/%](?!>)"))
      )
    )
  );
  override fun build() = Mode(
    aliases = listOf("ls",
 "lassoscript"),
    case_insensitive = true,
    lexemes = LASSO_IDENT_RE + "|&[lg]t;",

    keywords = keywords(LASSO_KEYWORDS),
    contains = listOf(
      Mode(
        className = "meta",

        begin = LASSO_CLOSE_RE,
        relevance = 0,
        starts = Mode( // markup
          end = "\\[|" +
 LASSO_ANGLE_RE,
          returnEnd = true,
          relevance = 0,
          contains = listOf(HTML_COMMENT)
        )
      ),
      LASSO_NOPROCESS,
      LASSO_START,
      Mode(
        className = "meta",

        begin = "\\[no_square_brackets",

        starts = Mode(
          end = "\\[\"\"\"no_square_brackets\\]",
 /""" not implemented in the language
          lexemes = LASSO_IDENT_RE + "|&[lg]t;",

          keywords = keywords(LASSO_KEYWORDS),
          contains = listOf(
            Mode(
              className = "meta",

              begin = LASSO_CLOSE_RE,
              relevance = 0,
              starts = Mode(
                end = "\\[noprocess\\]|" +
 LASSO_ANGLE_RE,
                returnEnd = true,
                contains = listOf(HTML_COMMENT)
              )
            ),
            LASSO_NOPROCESS,
            LASSO_START
          ) + LASSO_CODE)
        )
      ),
      Mode(
        className = "meta",

        begin = "\\[",

        relevance = 0
      ),
      Mode(
        className = "meta",

        begin = "^#!",
 end:"lasso9\$",

        relevance = 10
      )
    ) + LASSO_CODE)
  );
}
