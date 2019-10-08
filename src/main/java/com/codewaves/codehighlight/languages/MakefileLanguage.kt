package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Makefile
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Contributors = Joël Porquet <joel@porquet.org>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class MakefileLanguage : LanguageBuilder  {
  /* Variables = simple (eg $(var)) and special (eg $@) */
  val VARIABLE = Mode(
    className = "variable",

    variants = listOf(
      Mode(
        begin = "\\$\\(" +
 hljs.UNDERSCORE_IDENT_RE + "\\)",

        contains = listOf(hljs.BACKSLASH_ESCAPE)
      ),
      Mode(
        begin = """\${'$'}[@%<?\^\+\*]"""
      )
    )
  );
  /* Quoted string with variables inside */
  val QUOTE_STRING = Mode(
    className = "string",

    begin = """"""",

 end = """"""",

    contains = listOf(
      hljs.BACKSLASH_ESCAPE,
      VARIABLE
    )
  );
  /* Function = $(func arg,...) */
  val FUNC = Mode(
    className = "variable",

    begin = """\${'$'}\(listOf(\w-]+\s""",

 end = """\)""",

    keywords = listOf(
      Keyword(className = "built_in",

 value = "subst patsubst strip findstring filter filter-out sort word wordlist firstword lastword dir notdir suffix basename addsuffix addprefix join wildcard realpath abspath error warning shell origin flavor foreach if or and call eval file value")
    ),
    contains = listOf(
      VARIABLE
    )
  );
  /* Variable assignment */
  val VAR_ASSIG = Mode(
    begin = "^" +
 hljs.UNDERSCORE_IDENT_RE + "\\s*[:+?]?=",

    illegal = "\\n",

    returnBegin = true,
    contains = listOf(
      Mode(
        begin = "^" +
 hljs.UNDERSCORE_IDENT_RE,
 end = "[:+?]?=",

        excludeEnd = true
      )
    )
  );
  /* Meta targets (.PHONY) */
  val META = Mode(
    className = "meta",

    begin = """^\.PHONY:""",

 end = """${'$'}""",

    keywords = keywords(listOf(Keyword(className = "meta-keyword",

 value = ".PHONY"))),
    lexemes = """[\.\w]+"""
  );
  /* Targets */
  val TARGET = Mode(
    className = "section",

    begin = """^[^\s]+:""",

 end = """${'$'}""",

    contains = listOf(VARIABLE,)
  );
  override fun build() = Mode(
    aliases = listOf("mk",
 "mak"),
    keywords =       keywords("define endef undefine ifdef ifndef ifeq ifneq else endif include -include sinclude override export unexport private vpath"),
    lexemes = """[\w-]+""",

    contains = listOf(
      hljs.HASH_COMMENT_MODE,
      VARIABLE,
      QUOTE_STRING,
      FUNC,
      VAR_ASSIG,
      META,
      TARGET
    )
  );
}
