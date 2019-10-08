package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Delphi
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DelphiLanguage : LanguageBuilder  {
  val KEYWORDS = "exports register file shl array record property for mod while set ally label uses raise not stored class safecall val interface or private static exit index inherited to else stdcall override shr asm far resourcestring finalization packed virtual out and protected library do xorwrite goto near function end div overload object unit begin string on inline repeat until destructor write message program with read initialization except default nil if case cdecl in downto threadvar of try pascal const external constructor type public then implementation finally published procedure absolute reintroduce operator as is abstract alias assembler bitpacked break continue cppdecl cvar enumerator experimental platform deprecated unimplemented dynamic export far16 forward generic helper implements interrupt iochecks local name nodefault noreturn nostackframe oldfpccall otherwise saveregisters softfloat specialize strict unaligned varargs ";
  val COMMENT_MODES = listOf(
    hljs.C_LINE_COMMENT_MODE,
    hljs.COMMENT("""\{""",
 """\}""",
 Mode(relevance = 0)),
    hljs.COMMENT("""\(\*""",
 """\*\)""",
 Mode(relevance = 10))
  );
  val DIRECTIVE = Mode(
    className = "meta",

    variants = listOf(
      Mode(begin = """\{\${'$'}""",

 end = """\}"""),
      Mode(begin = """\(\*\${'$'}""",

 end = """\*\)""")
    )
  );
  val STRING = Mode(
    className = "string",

    begin = """'""",

 end = """'""",

    contains = listOf(Mode(begin = """''"""))
  );
  val CHAR_STRING = Mode(
    className = "string",

 begin = """(#\d+)+"""
  );
  val CLASS = Mode(
    begin = hljs.IDENT_RE + "\\s*=\\s*class\\s*\\(",

 returnBegin = true,
    contains = listOf(
      hljs.TITLE_MODE
    )
  );
  val FUNCTION = Mode(
    className = "function",

    beginKeywords = keywords("function constructor destructor procedure",

 end = """[:;]"""),
    keywords = keywords("function constructor|10 destructor|10 procedure|10"),
    contains = listOf(
      hljs.TITLE_MODE,
      Mode(
        className = "params",

        begin = """\(""",

 end = """\)""",

        keywords = keywords(KEYWORDS),
        contains = listOf(STRING, CHAR_STRING, DIRECTIVE) + COMMENT_MODES)
      ),
      DIRECTIVE
    ) + COMMENT_MODES)
  );
  override fun build() = Mode(
    aliases = listOf("dpr",
 "dfm",
 "pas",
 "pascal",
 "freepascal",
 "lazarus",
 "lpr",
 "lfm"),
    case_insensitive = true,
    keywords = keywords(KEYWORDS),
    illegal = """"|\${'$'}[G-Zg-z]|\/\*|<\/|\|""",

    contains = listOf(
      STRING, CHAR_STRING,
      hljs.NUMBER_MODE,
      CLASS,
      FUNCTION,
      DIRECTIVE
    ) + COMMENT_MODES)
  );
}
