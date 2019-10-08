package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Lisp
Description = Generic lisp syntax
Author = Vasily Polovnyov <vast@whiteants.net>
Category = lisp
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LispLanguage : LanguageBuilder  {
  val LISP_IDENT_RE = "[a-zA-Z_\\-\\+\\*\\/\\<\\=\\>\\&\\#][a-zA-Z0-9_\\-\\+\\*\\/\\<\\=\\>\\&\\#!]*";
  val MEC_RE = "\\|[^]*?\\|";
  val LISP_SIMPLE_NUMBER_RE = "(\\-|\\+)?\\d+(\\.\\d+|\\/\\d+)?((d|e|f|l|s|D|E|F|L|S)(\\+|\\-)?\\d+)?";
  val SHEBANG = Mode(
    className = "meta",

    begin = "^#!",

 end = "\$"
  );
  val LITERAL = Mode(
    className = "literal",

    begin = "\\b(t{1}|nil)\\b"
  );
  val NUMBER = Mode(
    className = "number",

    variants = listOf(
      Mode(begin = LISP_SIMPLE_NUMBER_RE,
 relevance = 0),
      Mode(begin = "#(b|B)[0-1]+(/[0-1]+)?"),
      Mode(begin = "#(o|O)[0-7]+(/[0-7]+)?"),
      Mode(begin = "#(x|X)[0-9a-fA-F]+(/[0-9a-fA-F]+)?"),
      Mode(begin = "#(c|C)\\(" +
 LISP_SIMPLE_NUMBER_RE +  + LISP_SIMPLE_NUMBER_RE,
 end = "\\)")
    )
  );
  val STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null));
  val COMMENT = hljs.COMMENT(
    ";",
 "\$",

    Mode(
      relevance = 0
    )
  );
  val VARIABLE = Mode(
    begin = "\\*",

 end = "\\*"
  );
  val KEYWORD = Mode(
    className = "symbol",

    begin = "[:&]" +
 LISP_IDENT_RE
  );
  val IDENT = Mode(
    begin = LISP_IDENT_RE,
    relevance = 0
  );
  val MEC = Mode(
    begin = MEC_RE
  );
  val QUOTED_LIST = Mode(
    begin = "\\(",

 end = "\\)",

    contains = listOf(
 LITERAL, STRING, NUMBER, IDENT)
  );
  val QUOTED = Mode(
    contains = listOf(NUMBER, STRING, VARIABLE, KEYWORD, QUOTED_LIST, IDENT),
    variants = listOf(
      Mode(
        begin = "[\'`]\\(",

 end = "\\)"
      ),
      Mode(
        begin = "\\(quote ",

 end = "\\)",

        keywords = keywords(listOf(Keyword(className = "name",

 value = "quote")))
      ),
      Mode(
        begin = "\'" +
 MEC_RE
      )
    )
  );
  val QUOTED_ATOM = Mode(
    variants = listOf(
      Mode(begin = "\'" +
 LISP_IDENT_RE),
      Mode(begin = "#\'" +
 LISP_IDENT_RE + "(::" +
 LISP_IDENT_RE + ")*")
    )
  );
  val LIST = Mode(
    begin = "\\(\\s*",

 end = "\\)"
  );
  val BODY = Mode(
    endsWithParent = true,
    relevance = 0
  );
  LIST.contains = listOf(
    Mode(
      className = "name",

      variants = listOf(
        Mode(begin = LISP_IDENT_RE),
        Mode(begin = MEC_RE)
      )
    ),
    BODY
  );
  BODY.contains = listOf(QUOTED, QUOTED_ATOM, LIST, LITERAL, NUMBER, STRING, COMMENT, VARIABLE, KEYWORD, MEC, IDENT);

  override fun build() = Mode(
    illegal = """\S""",

    contains = listOf(
      NUMBER,
      SHEBANG,
      LITERAL,
      STRING,
      COMMENT,
      QUOTED,
      QUOTED_ATOM,
      LIST,
      IDENT
    )
  );
}
