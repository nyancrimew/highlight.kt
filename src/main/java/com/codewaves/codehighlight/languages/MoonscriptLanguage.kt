package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = MoonScript
Author = Billy Quith <chinbillybilbo@gmail.com>
Description = MoonScript is a programming language that transcompiles to Lua. For info about language see http://moonscript.org/
Origin = coffeescript.js
Category = scripting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class MoonscriptLanguage : LanguageBuilder  {
  val KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = // Moonscript keywords
      "if then not for in while do return else elseif break continue switch and or unless when class extends super local import export from using"),
    Keyword(className = "literal",

 value = "true false nil"),
    Keyword(className = "built_in",

 value = "_G _VERSION assert collectgarbage dofile error getfenv getmetatable ipairs load loadfile loadstring module next pairs pcall print rawequal rawget rawset require select setfenv setmetatable tonumber tostring type unpack xpcall coroutine debug io math os package string table"
  ));
  val JS_IDENT_RE = "[A-Za-z\$_][0-9A-Za-z\$_]*";
  val SUBST = Mode(
    className = "subst",

    begin = """#\{""",

 end = """}""",

    keywords = KEYWORDS
  );
  val EXPRESSIONS = listOf(
    hljs.inherit(hljs.C_NUMBER_MODE,
      Mode(starts = Mode(end = "(\\s*/)?",

 relevance = 0))), // a number tries to eat the following slash to prevent treating it as a regexp
    Mode(
      className = "string",

      variants = listOf(
        Mode(
          begin = """'""",

 end = """'""",

          contains = listOf(hljs.BACKSLASH_ESCAPE)
        ),
        Mode(
          begin = """"""",

 end = """"""",

          contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST)
        )
      )
    ),
    Mode(
      className = "built_in",

      begin = "@__" +
 hljs.IDENT_RE
    ),
    Mode(
      begin = "@" +
 hljs.IDENT_RE // relevance booster on par with CoffeeScript
    ),
    Mode(
      begin = hljs.IDENT_RE + "\\\\" +
 hljs.IDENT_RE // inst\method
    )
  );
  SUBST.contains = EXPRESSIONS;

  val TITLE = hljs.inherit(hljs.TITLE_MODE, Mode(begin = JS_IDENT_RE));
  val PARAMS_RE = "(\\(.*\\))?\\s*\\B[-=]>";
  val PARAMS = Mode(
    className = "params",

    begin = "\\(listOf(^\\(]",

 returnBegin = true,
    /* We need another contained nameless mode to not have every nested
    pair of parens to be called "params" */
    contains = listOf(Mode(
      begin = """\(""",

 end = """\)""",

      keywords = keywords(KEYWORDS),
      contains = listOf("self") + EXPRESSIONS)
    ))
  );

  override fun build() = Mode(
    aliases = listOf("moon"),
    keywords = keywords(KEYWORDS),
    illegal = """\/\*""",

    contains = EXPRESSIONS + listOf(
      hljs.COMMENT("--",
 "\$"),
      Mode(
        className = "function",
  // function = -> =>
        begin = "^\\s*" +
 JS_IDENT_RE + "\\s*=\\s*" +
 PARAMS_RE,
 end = "[-=]>",

        returnBegin = true,
        contains = listOf(TITLE, PARAMS)
      ),
      Mode(
        begin = """[\(,:=]\s*""",
 // anonymous function start
        relevance = 0,
        contains = listOf(
          Mode(
            className = "function",

            begin = PARAMS_RE,
 end = "[-=]>",

            returnBegin = true,
            contains = listOf(PARAMS)
          )
        )
      ),
      Mode(
        className = "class",

        beginKeywords = keywords("class"),
        end = "\$",

        illegal = """[:="\[\]]\"\"",

        contains = listOf(
          Mode(
            beginKeywords = keywords("extends"),
            endsWithParent = true,
            illegal = """[:="\[\]]\"\"",

            contains = listOf(TITLE)
          ),
          TITLE
        )
      ),
      Mode(
        className = "name",
    // table
        begin = JS_IDENT_RE + ":",

 end = ":",

        returnBegin = true,
 returnEnd = true,
        relevance = 0
      )
    ))
  );
}
