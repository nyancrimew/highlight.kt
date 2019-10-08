package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Ruby
Author = Anton Kovalyov <anton@kovalyov.net>
Contributors = Peter Leonov <gojpeg@yandex.ru>, Vasily Polovnyov <vast@whiteants.net>, Loren Segal <lsegal@soen.ca>, Pascal Hurni <phi@ruby-reactive.org>, Cedric Sohrauer <sohrauer@googlemail.com>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class RubyLanguage : LanguageBuilder  {
  val RUBY_METHOD_RE = "[a-zA-Z_]\\w*[!?=]?|[-+~]\\@|<<|>>|=~|===?|<=>|[<>]=?|\\*\\*|[-/+%^&*~`|]|\\[\\]=?";
  val RUBY_KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "and then defined module in return redo if BEGIN retry end for self when next until do begin unless END rescue else break undef not super class case require yield alias while ensure elsif or include attr_reader attr_writer attr_accessor"),
    Keyword(className = "literal",

 value = "true false nil"
  ));
  val YARDOCTAG = Mode(
    className = "doctag",

    begin = "@[A-Za-z]+"
  );
  val IRB_OBJECT = Mode(
    begin = "#<",

 end = ">"
  );
  val COMMENT_MODES = listOf(
    hljs.COMMENT(
      "#",

      "\$",

      Mode(
        contains = listOf(YARDOCTAG)
      )
    ),
    hljs.COMMENT(
      "^\\=begin",

      "^\\=end",

      Mode(
        contains = listOf(YARDOCTAG),
        relevance = 10
      )
    ),
    hljs.COMMENT("^__END__",
 "\\n\$")
  );
  val SUBST = Mode(
    className = "subst",

    begin = "#\\{",

 end = "}",

    keywords = RUBY_KEYWORDS
  );
  val STRING = Mode(
    className = "string",

    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
    variants = listOf(
      Mode(begin = """'""",

 end = """'"""),
      Mode(begin = """"""",

 end = """""""),
      Mode(begin = """`""",

 end = """`"""),
      Mode(begin = "%[qQwWx]?\\(",

 end = "\\)"),
      Mode(begin = "%[qQwWx]?\\[",

 end = "\\]"),
      Mode(begin = "%[qQwWx]?{",

 end = "}"),
      Mode(begin = "%[qQwWx]?<",

 end = ">"),
      Mode(begin = "%[qQwWx]?/",

 end = "/"),
      Mode(begin = "%[qQwWx]?%",

 end = "%"),
      Mode(begin = "%[qQwWx]?-",

 end = "-"),
      Mode(begin = "%[qQwWx]?\\|",

 end = "\\|"),
      Mode(
        // \B in the beginning suppresses recognition of ?-sequences where ?
        // is the last character of a preceding identifier, as in = `func?4`
        begin = """\B\?(\\\d{1,3}|\\x[A-Fa-f0-9]{1,2}|\\u[A-Fa-f0-9]{4}|\\?\S)\b"""
      ),
      Mode( // heredocs
        begin = """<<[-~]?'?(\w+)(?:.|\n)*?\n\s*\1\b""",

        returnBegin = true,
        contains = listOf(
          Mode( begin = """<<[-~]?'?""" ),
          Mode( begin = """\w+""",

            endSameAsBegin = true,
            contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
          )
        )
      )
    )
  );
  val PARAMS = Mode(
    className = "params",

    begin = "\\(",

 end = "\\)",

 endsParent = true,
    keywords = RUBY_KEYWORDS
  );

  val RUBY_DEFAULT_CONTAINS = listOf(
    STRING,
    IRB_OBJECT,
    Mode(
      className = "class",

      beginKeywords = keywords("class module",

 end = "\$|;"),
      illegal = """=""",

      contains = listOf(
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = "[A-Za-z_]\\w*(::\\w+)*(\\?|\\!)?")),
        Mode(
          begin = "<\\s*",

          contains = listOf(Mode(
            begin = "(" +
 hljs.IDENT_RE + "::)?" +
 hljs.IDENT_RE
          ))
        )
      ) + COMMENT_MODES)
    ),
    Mode(
      className = "function",

      beginKeywords = keywords("def",

 end = "\$|;"),
      contains = listOf(
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = RUBY_METHOD_RE)),
        PARAMS
      ) + COMMENT_MODES)
    ),
    Mode(
      // swallow namespace qualifiers before symbols
      begin = hljs.IDENT_RE + "::"
    ),
    Mode(
      className = "symbol",

      begin = hljs.UNDERSCORE_IDENT_RE + "(\\!|\\?)?:",

      relevance = 0
    ),
    Mode(
      className = "symbol",

      begin = ":(?!\\s)",

      contains = listOf(STRING, Mode(begin = RUBY_METHOD_RE)),
      relevance = 0
    ),
    Mode(
      className = "number",

      begin = "(\\b0[0-7_]+)|(\\b0x[0-9a-fA-F_]+)|(\\b[1-9][0-9_]*(\\.[0-9_]+)?)|[0_]\\b",

      relevance = 0
    ),
    Mode(
      begin = "(\\$\\W)|((\\$|\\@\\@?)(\\w+))" // variables
    ),
    Mode(
      className = "params",

      begin = """\|""",

 end = """\|""",

      keywords = RUBY_KEYWORDS
    ),
    Mode( // regexp container
      begin = "(" +
 hljs.RE_STARTERS_RE + "|unless)\\s*",

      keywords = keywords("unless"),
      contains = listOf(
        IRB_OBJECT,
        Mode(
          className = "regexp",

          contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
          illegal = """\n""",

          variants = listOf(
            Mode(begin = "/",

 end = "/[a-z]*"),
            Mode(begin = "%r{",

 end = "}listOf(a-z]*"),
            Mode(begin = "%r\\(",

 end = "\\)[a-z]*"),
            Mode(begin = "%r!",

 end = "![a-z]*"),
            Mode(begin = "%r\\[",

 end = "\\][a-z]*")
          )
        )
      ) + COMMENT_MODES),
      Keyword(className = "relevance",

 value = 0
    ))
  ) + COMMENT_MODES);

  SUBST.contains = RUBY_DEFAULT_CONTAINS;
  PARAMS.contains = RUBY_DEFAULT_CONTAINS;

  val SIMPLE_PROMPT = "[>?]>";
  val DEFAULT_PROMPT = "[\\w#]+\\(\\w+\\):\\d+:\\d+>";
  val RVM_PROMPT = "(\\w+-)?\\d+\\.\\d+\\.\\d(p\\d+)?[^>]+>";

  val IRB_DEFAULT = listOf(
    Mode(
      begin = """^\s*=>""",

      starts = Mode(
        end = "\$",

 contains = RUBY_DEFAULT_CONTAINS
      )
    ),
    Mode(
      className = "meta",

      begin = "^(\"+SIMPLE_PROMPT+\"|\"+DEFAULT_PROMPT+\"|\"+RVM_PROMPT+\")",

      starts = Mode(
        end = "\$",

 contains = RUBY_DEFAULT_CONTAINS
      )
    )
  );

  override fun build() = Mode(
    aliases = listOf("rb",
 "gemspec",
 "podspec",
 "thor",
 "irb"),
    keywords = keywords(RUBY_KEYWORDS),
    illegal = """\/\*""",

    contains = COMMENT_MODES + IRB_DEFAULT) + RUBY_DEFAULT_CONTAINS)
  );
}
