package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Crystal
Author = TSUYUSATO Kitsune <make.just.on@gmail.com>
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CrystalLanguage : LanguageBuilder  {
  val INT_SUFFIX = "(_*[ui](8|16|32|64|128))?";
  val FLOAT_SUFFIX = "(_*f(32|64))?";
  val CRYSTAL_IDENT_RE = "[a-zA-Z_]\\w*[!?=]?";
  val CRYSTAL_METHOD_RE = "[a-zA-Z_]\\w*[!?=]?|[-+~]\\@|<<|>>|=~|===?|<=>|[<>]=?|\\*\\*|[-/+%^&*~|]|//|//=|&[-+*]=?|&\\*\\*|\\[\\][=?]?";
  val CRYSTAL_PATH_RE = "[A-Za-z_]\\w*(::\\w+)*(\\?|\\!)?";
  val CRYSTAL_KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "abstract alias annotation as as? asm begin break case class def do else elsif end ensure enum extend for fun if include instance_sizeof is_a? lib macro module next nil? of out pointerof private protected rescue responds_to? return require select self sizeof struct super then type typeof union uninitialized unless until verbatim when while with yield __DIR__ __END_LINE__ __FILE__ __LINE__"),
    Keyword(className = "literal",

 value = "false nil true"
  ));
  val SUBST = Mode(
    className = "subst",

    begin = "#{",

 end = "}",

    keywords = CRYSTAL_KEYWORDS
  );
  val EXPANSION = Mode(
    className = "template-variable",

    variants = listOf(
      Mode(begin = "\\{\\{",

 end = "\\}\\}"),
      Mode(begin = "\\{%",

 end = "%\\}")
    ),
    keywords = CRYSTAL_KEYWORDS
  );

  function recursiveParen(begin, end) {
    val     contains = listOf(Mode(begin = begin,
 end = end));
    contains[0].contains = contains;
    return contains;
  )
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
      Mode(begin = "%[Qwi]?\\(",

 end = "\\)",

 contains = recursiveParen("\\(",
 "\\)")),
      Mode(begin = "%[Qwi]?\\[",

 end = "\\]",

 contains = recursiveParen("\\[",
 "\\]")),
      Mode(begin = "%[Qwi]?{",

 end = "}",

 contains = recursiveParen("{",
 "}")),
      Mode(begin = "%[Qwi]?<",

 end = ">",

 contains = recursiveParen("<",
 ">")),
      Mode(begin = "%[Qwi]?\\|",

 end = "\\|"),
      Mode(begin = """<<-\w+${'$'}""",

 end = """^\s*\w+${'$'}""")
    ),
    relevance = 0
  );
  val Q_STRING = Mode(
    className = "string",

    variants = listOf(
      Mode(begin = "%q\\(",

 end = "\\)",

 contains = recursiveParen("\\(",
 "\\)")),
      Mode(begin = "%q\\[",

 end = "\\]",

 contains = recursiveParen("\\[",
 "\\]")),
      Mode(begin = "%q{",

 end = "}",

 contains = recursiveParen("{",
 "}")),
      Mode(begin = "%q<",

 end = ">",

 contains = recursiveParen("<",
 ">")),
      Mode(begin = "%q\\|",

 end = "\\|"),
      Mode(begin = """<<-'\w+"\$\"\"",

 end = """^\s*\w+${'$'}""")
    ),
    relevance = 0
  );
  val REGEXP = Mode(
    begin = "(?!%))(" +
 hljs.RE_STARTERS_RE + "|\\n|\\b(case|if|select|unless|until|when|while)\\b)\\s*",

    keywords = keywords("case if select unless until when while"),
    contains = listOf(
      Mode(
        className = "regexp",

        contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
        variants = listOf(
          Mode(begin = "//[a-z]*",

 relevance = 0),
          Mode(begin = "/(?!\\/)",

 end = "/[a-z]*")
        )
      )
    ),
    relevance = 0
  );
  val REGEXP2 = Mode(
    className = "regexp",

    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
    variants = listOf(
      Mode(begin = "%r\\(",

 end = "\\)",

 contains = recursiveParen("\\(",
 "\\)")),
      Mode(begin = "%r\\[",

 end = "\\]",

 contains = recursiveParen("\\[",
 "\\]")),
      Mode(begin = "%r{",

 end = "}",

 contains = recursiveParen("{",
 "}")),
      Mode(begin = "%r<",

 end = ">",

 contains = recursiveParen("<",
 ">")),
      Mode(begin = "%r\\|",

 end = "\\|")
    ),
    relevance = 0
  );
  val ATTRIBUTE = Mode(
    className = "meta",

    begin = "@\\[",

 end = "\\]",

    contains = listOf(
      hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(className = "meta-string"))
    )
  );
  val CRYSTAL_DEFAULT_CONTAINS = listOf(
    EXPANSION,
    STRING,
    Q_STRING,
    REGEXP2,
    REGEXP,
    ATTRIBUTE,
    hljs.HASH_COMMENT_MODE,
    Mode(
      className = "class",

      beginKeywords = keywords("class module struct",

 end = "\$|;"),
      illegal = """=""",

      contains = listOf(
        hljs.HASH_COMMENT_MODE,
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = CRYSTAL_PATH_RE)),
        Mode(begin = "<") // relevance booster for inheritance
      )
    ),
    Mode(
      className = "class",

      beginKeywords = keywords("lib enum union",

 end = "\$|;"),
      illegal = """=""",

      contains = listOf(
        hljs.HASH_COMMENT_MODE,
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = CRYSTAL_PATH_RE))
      ),
      relevance = 10
    ),
    Mode(
      beginKeywords = keywords("annotation",

 end = "\$|;"),
      illegal = """=""",

      contains = listOf(
        hljs.HASH_COMMENT_MODE,
        hljs.inherit(hljs.TITLE_MODE, Mode(begin = CRYSTAL_PATH_RE))
      ),
      relevance = 10
    ),
    Mode(
      className = "function",

      beginKeywords = keywords("def",

 end = """\B\b"""),
      contains = listOf(
        hljs.inherit(hljs.TITLE_MODE, Mode(
          begin = CRYSTAL_METHOD_RE,
          endsParent = true
        ))
      )
    ),
    Mode(
      className = "function",

      beginKeywords = keywords("fun macro",

 end = """\B\b"""),
      contains = listOf(
        hljs.inherit(hljs.TITLE_MODE, Mode(
          begin = CRYSTAL_METHOD_RE,
          endsParent = true
        ))
      ),
      relevance = 5
    ),
    Mode(
      className = "symbol",

      begin = hljs.UNDERSCORE_IDENT_RE + "(\\!|\\?)?:",

      relevance = 0
    ),
    Mode(
      className = "symbol",

      begin = ":",

      contains = listOf(STRING, Mode(begin = CRYSTAL_METHOD_RE)),
      relevance = 0
    ),
    Mode(
      className = "number",

      variants = listOf(
        Mode( begin = "\\b0b(listOf(01_]+)" +
 INT_SUFFIX ),
        Mode( begin = "\\b0o(listOf(0-7_]+)" +
 INT_SUFFIX ),
        Mode( begin = "\\b0x(listOf(A-Fa-f0-9_]+)" +
 INT_SUFFIX ),
        Mode( begin = "\\b(listOf(1-9][0-9_]*[0-9]|[0-9))(\\.[0-9][0-9_]*)?(listOf(eE]_*[-+]?[0-9_]*)?" +
 FLOAT_SUFFIX + "(?!_)" ),
        Mode( begin = "\\b(listOf(1-9][0-9_]*|0)" +
 INT_SUFFIX )
      ),
      relevance = 0
    )
  );
  SUBST.contains = CRYSTAL_DEFAULT_CONTAINS;
  EXPANSION.contains = CRYSTAL_DEFAULT_CONTAINS.slice(1); // without EXPANSION

  override fun build() = Mode(
    aliases = listOf("cr"),
    lexemes = CRYSTAL_IDENT_RE,
    keywords = keywords(CRYSTAL_KEYWORDS),
    contains = CRYSTAL_DEFAULT_CONTAINS
  );
}
