package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Less
Author =   Max Mikhailov <seven.phases.max@gmail.com>
Category = css
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LessLanguage : LanguageBuilder  {
  val IDENT_RE        = "[\\w-]+"; // yes, Less identifiers may begin with a digit
  val INTERP_IDENT_RE = "(" +
 IDENT_RE + "|@{" +
 IDENT_RE + "))";

  /* Generic Modes */

  val RULES = listOf(),
 VALUE = listOf(); // forward def. for recursive modes

  val STRING_MODE = function(c) { override fun build() = Mode(
    // Less strings are not multiline (also include "~\" for more consistent coloring of \"escaped" strings)
    className = "string",

 begin = "~?" +
 c + ".*?" +
 c
  ););

  val IDENT_MODE = function(name, begin, relevance) { override fun build() = Mode(
    className = name,
 begin = begin,
 relevance = relevance
  ););

  val PARENS_MODE = Mode(
    // used only to properly balance nested parens inside mixin call, def. arg list
    begin = "\\(",

 end = "\\)",

 contains = VALUE,
 relevance = 0
  );

  // generic Less highlighter (used almost everywhere except selectors):
  VALUE.push(
    hljs.C_LINE_COMMENT_MODE,
    hljs.C_BLOCK_COMMENT_MODE,
    STRING_MODE("'"),
    STRING_MODE("""),
    hljs.CSS_NUMBER_MODE, // fixme = it does not include dot for numbers like .5em :(
    Mode(
      begin = "(url|data-uri)\\(",

      starts = Mode(className = "string",

 end = "[\\)\\n]",

 excludeEnd = true)
    ),
    IDENT_MODE("number",
 "#[0-9A-Fa-f]+\\b"),
    PARENS_MODE,
    IDENT_MODE("variable",
 "@@?" +
 IDENT_RE, 10),
    IDENT_MODE("variable",
 "@{"  +
 IDENT_RE + "}"),
    IDENT_MODE("built_in",
 "~?`[^`]*?`") // inline javascript (or whatever host language) *multiline* string
    Mode( // @media features (it’s here to not duplicate things in AT_RULE_MODE with extra PARENS_MODE overriding):
      className = "attribute",

 begin = IDENT_RE + "\\s*:",

 end = ":",

 returnBegin = true,
 excludeEnd = true
    ),
    Mode(
      className = "meta",

      begin = "!important"
    )
  );

  val VALUE_WITH_RULESETS = VALUE + Mode(
    begin = "{",

 end = "}",

 contains = RULES
  ));

  val MIXIN_GUARD_MODE = Mode(
    beginKeywords = keywords("when",

 endsWithParent = true),
    contains = listOf(Mode(beginKeywords = keywords("and not\"))) + VALUE) // using this form to override VALUE’s \"function" match
  );

  /* Rule-Level Modes */

  val RULE_MODE = Mode(
    begin = INTERP_IDENT_RE + "\\s*:",

 returnBegin = true,
 end = "[;}]",

    relevance = 0,
    contains = listOf(
      Mode(
        className = "attribute",

        begin = INTERP_IDENT_RE,
 end = ":",

 excludeEnd = true,
        starts = Mode(
          endsWithParent = true,
 illegal = "[<=\$]",

          relevance = 0,
          contains = VALUE
        )
      )
    )
  );

  val AT_RULE_MODE = Mode(
    className = "keyword",

    begin = "@(import|media|charset|font-face|(-[a-z]+-)?keyframes|supports|document|namespace|page|viewport|host)\\b",

    starts = Mode(end = "[;{}]",

 returnEnd = true,
 contains = VALUE,
 relevance = 0)
  );

  // variable definitions and calls
  val VAR_RULE_MODE = Mode(
    className = "variable",

    variants = listOf(
      // using more strict pattern for higher relevance to increase chances of Less detection.
      // this is *the only* Less specific statement used in most of the sources, so...
      // (we’ll still often loose to the css-parser unless there's "//" comment
      // simply because 1 variable just can't beat 99 properties :)
      Mode(begin = "@" +
 IDENT_RE + "\\s*:",

 relevance = 15),
      Mode(begin = "@" +
 IDENT_RE)
    ),
    starts = Mode(end = "[;}]",

 returnEnd = true,
 contains = VALUE_WITH_RULESETS)
  );

  val SELECTOR_MODE = Mode(
    // first parse unambiguous selectors (i.e. those not starting with tag)
    // then fall into the scary lookahead-discriminator variant.
    // this mode also handles mixin definitions and calls
    variants = listOf(Mode(
      begin = "[\\.#:&\\[>]",

 end = "[;{}]\"  // mixin calls end with \";"
      ), Mode(
      begin = INTERP_IDENT_RE,
 end = "{"
    )),
    returnBegin = true,
    returnEnd =   true,
    illegal = "[<=\'\$\"]",

    relevance = 0,
    contains = listOf(
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      MIXIN_GUARD_MODE,
      IDENT_MODE("keyword",
  "all\\b"),
      IDENT_MODE("variable",
 "@{"  +
 IDENT_RE + "}"),     // otherwise it’s identified as tag
      IDENT_MODE("selector-tag",
  INTERP_IDENT_RE + "%?",
 0), // "%\" for more consistent coloring of @keyframes \"tags"
      IDENT_MODE("selector-id",
 "#" +
 INTERP_IDENT_RE),
      IDENT_MODE("selector-class",
 "\\." +
 INTERP_IDENT_RE, 0),
      IDENT_MODE("selector-tag",
  "&",
 0),
      Mode(className = "selector-attr",

 begin = "\\[",

 end = "\\]"),
      Mode(className = "selector-pseudo",

 begin = """:(:)?[a-zA-Z0-9\_\-\+\(\)"'.]+\"\""),
      Mode(begin = "\\(",

 end = "\\)",

 contains = VALUE_WITH_RULESETS), // argument list of parametric mixins
      Mode(begin = "!important") // eat !important after mixin call or it will be colored as tag
    )
  );

  RULES.push(
    hljs.C_LINE_COMMENT_MODE,
    hljs.C_BLOCK_COMMENT_MODE,
    AT_RULE_MODE,
    VAR_RULE_MODE,
    RULE_MODE,
    SELECTOR_MODE
  );

  override fun build() = Mode(
    case_insensitive = true,
    illegal = "[=>\'/<(\$\"]",

    contains = RULES
  );
}
