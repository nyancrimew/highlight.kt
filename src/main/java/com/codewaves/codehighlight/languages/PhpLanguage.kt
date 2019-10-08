package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = PHP
Author = Victor Karamzin <Victor.Karamzin@enterra-inc.com>
Contributors = Evgeny Stepanischev <imbolk@gmail.com>, Ivan Sagalaev <maniac@softwaremaniacs.org>
Category = common
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class PhpLanguage : LanguageBuilder  {
  val VARIABLE = Mode(
    begin = "\\$+[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*"
  );
  val PREPROCESSOR = Mode(
    className = "meta",

 begin = """<\?(php)?|\?>"""
  );
  val STRING = Mode(
    className = "string",

    contains = listOf(hljs.BACKSLASH_ESCAPE, PREPROCESSOR),
    variants = listOf(
      Mode(
        begin = "b\"",

 end = "\""
      ),
      Mode(
        begin = "b\'",

 end = "\'"
      ),
      hljs.inherit(hljs.APOS_STRING_MODE, Mode(illegal = null)),
      hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null))
    )
  );
  val NUMBER = Mode(variants = listOf(hljs.BINARY_NUMBER_MODE, hljs.C_NUMBER_MODE));
  override fun build() = Mode(
    aliases = listOf("php",
 "php3",
 "php4",
 "php5",
 "php6",
 "php7"),
    case_insensitive = true,
    keywords =       keywords("and include_once list abstract global private echo interface as static endswitch array null if endwhile or const for endforeach self val while isset public protected exit foreach throw elseif include __FILE__ empty require_once do xor return parent clone use __CLASS__ __LINE__ else break print eval new catch __METHOD__ case exception default die require __FUNCTION__ enddeclare final try switch continue endfor endif declare unset true false trait goto instanceof insteadof __DIR__ __NAMESPACE__ yield finally"),
    contains = listOf(
      hljs.HASH_COMMENT_MODE,
      hljs.COMMENT("//",
 "\$",
 Mode(contains = listOf(PREPROCESSOR))),
      hljs.COMMENT(
        "/\\*",

        "\\*/",

        Mode(
          contains = listOf(
            Mode(
              className = "doctag",

              begin = "@[A-Za-z]+"
            )
          )
        )
      ),
      hljs.COMMENT(
        "__halt_compiler.+?;",

        false,
        Mode(
          endsWithParent = true,
          keywords = keywords("__halt_compiler"),
          lexemes = hljs.UNDERSCORE_IDENT_RE
        )
      ),
      Mode(
        className = "string",

        begin = """<<<["\"]?\w+[\"\"]?\$\"\"",

 end = """^\w+;?${'$'}""",

        contains = listOf(
          hljs.BACKSLASH_ESCAPE,
          Mode(
            className = "subst",

            variants = listOf(
              Mode(begin = """\${'$'}\w+"""),
              Mode(begin = """\{\${'$'}""",

 end = """\}""")
            )
          )
        )
      ),
      PREPROCESSOR,
      Mode(
        className = "keyword",

 begin = """\${'$'}this\b"""
      ),
      VARIABLE,
      Mode(
        // swallow composed identifiers to avoid parsing them as keywords
        begin = """(::|->)+[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*"""
      ),
      Mode(
        className = "function",

        beginKeywords = keywords("function",

 end = """[;{]""",

 excludeEnd = true),
        illegal = "\\$|\\[|%",

        contains = listOf(
          hljs.UNDERSCORE_TITLE_MODE,
          Mode(
            className = "params",

            begin = "\\(",

 end = "\\)",

            contains = listOf(
              

              VARIABLE,
              hljs.C_BLOCK_COMMENT_MODE,
              STRING,
              NUMBER
            )
          )
        )
      ),
      Mode(
        className = "class",

        beginKeywords = keywords("class interface",

 end = "{",

 excludeEnd = true),
        illegal = """[:\(\$"]\"\"",

        contains = listOf(
          Mode(beginKeywords = keywords("extends implements")),
          hljs.UNDERSCORE_TITLE_MODE
        )
      ),
      Mode(
        beginKeywords = keywords("namespace",

 end = ";"),
        illegal = """[\."]\"\"",

        contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
      ),
      Mode(
        beginKeywords = keywords("use",

 end = ";"),
        contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
      ),
      Mode(
        begin = "=>" // No markup, just a relevance booster
      ),
      STRING,
      NUMBER
    )
  );
}
