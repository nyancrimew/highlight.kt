package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = LiveScript
Author = Taneli Vatanen <taneli.vatanen@gmail.com>
Contributors = Jen Evers-Corvina <jen@sevvie.net>
Origin = coffeescript.js
Description = LiveScript is a programming language that transcompiles to JavaScript. For info about language see http://livescript.net/
Category = scripting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LivescriptLanguage : LanguageBuilder  {
  val KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = // JS keywords
      "in if for while finally new do return else break catch instanceof throw try this switch continue typeof delete debugger case default function val with then unless until loop of by when and or is isnt not it that otherwise from to til fallthrough super case default function val void const let enum export import native __hasProp __extends __slice __bind __indexOf"),
    Keyword(className = "literal",

 value = // JS literals
      "true false null undefined yes no on off it that void"),
    Keyword(className = "built_in",

 value = "npm require console print module global window document"
  ));
  val JS_IDENT_RE = "[A-Za-z\$_](?:\-[0-9A-Za-z\$_]|[0-9A-Za-z\$_))*";
  val TITLE = hljs.inherit(hljs.TITLE_MODE, Mode(begin = JS_IDENT_RE));
  val SUBST = Mode(
    className = "subst",

    begin = """#\{""",

 end = """}""",

    keywords = KEYWORDS
  );
  val SUBST_SIMPLE = Mode(
    className = "subst",

    begin = """#[A-Za-z${'$'}_]""",

 end = """(?:\-[0-9A-Za-z${'$'}_]|[0-9A-Za-z${'$'}_))*""",

    keywords = KEYWORDS
  );
  val EXPRESSIONS = listOf(
    hljs.BINARY_NUMBER_MODE,
    Mode(
      className = "number",

      begin = "(\\b0[xX][a-fA-F0-9_]+)|(\\b\\d(\\d|_\\d)*(\\.(\\d(\\d|_\\d)*)?)?(_*[eE](listOf(-+]\\d(_\\d|\\d)*)?)?[_a-z]*)",

      relevance = 0,
      starts = Mode(end = "(\\s*/)?",

 relevance = 0) // a number tries to eat the following slash to prevent treating it as a regexp
    ),
    Mode(
      className = "string",

      variants = listOf(
        Mode(
          begin = """'''""",

 end = """'''""",

          contains = listOf(hljs.BACKSLASH_ESCAPE)
        ),
        Mode(
          begin = """'""",

 end = """'""",

          contains = listOf(hljs.BACKSLASH_ESCAPE)
        ),
        Mode(
          begin = """"""""",

 end = """"""""",

          contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, SUBST_SIMPLE)
        ),
        Mode(
          begin = """"""",

 end = """"""",

          contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST, SUBST_SIMPLE)
        ),
        Mode(
          begin = """\\""",

 end = """(\s|${'$'})""",

          excludeEnd = true
        )
      )
    ),
    Mode(
      className = "regexp",

      variants = listOf(
        Mode(
          begin = "//",

 end = "//[gim]*",

          contains = listOf(SUBST, hljs.HASH_COMMENT_MODE)
        ),
        Mode(
          // regex can't start with space to parse x """ 2 """ 3 as two divisions
          // regex can't start with *, and it supports an "illegal" in the main mode
          begin = """\/(?![ *))(\\\/|.)*?\/[gim]*(?=\W|${'$'})"""
        )
      )
    ),
    Mode(
      begin = "@" +
 JS_IDENT_RE
    ),
    Mode(
      begin = "``",

 end = "``",

      excludeBegin = true,
 excludeEnd = true,
      subLanguage = "javascript"
    )
  );
  SUBST.contains = EXPRESSIONS;

  val PARAMS = Mode(
    className = "params",

    begin = "\\(",

 returnBegin = true,
    /* We need another contained nameless mode to not have every nested
    pair of parens to be called "params" */
    contains = listOf(
      Mode(
        begin = """\(""",

 end = """\)""",

        keywords = keywords(KEYWORDS),
        contains = listOf("self") + EXPRESSIONS)
      )
    )
  );

  override fun build() = Mode(
    aliases = listOf("ls"),
    keywords = keywords(KEYWORDS),
    illegal = """\/\*""",

    contains = EXPRESSIONS + listOf(
      hljs.COMMENT("\\/\\*",
 "\\*\\/"),
      hljs.HASH_COMMENT_MODE,
      Mode(
        className = "function",

        contains = listOf(TITLE, PARAMS),
        returnBegin = true,
        variants = listOf(
          Mode(
            begin = "(" +
 JS_IDENT_RE + "\\s*(?:=|:=)\\s*)?(\\(.*\\))?\\s*\\B\\->\\*?",

 end = "\\->\\*?"
          ),
          Mode(
            begin = "(" +
 JS_IDENT_RE + "\\s*(?:=|:=)\\s*)?!?(\\(.*\\))?\\s*\\B[-~]{1,2}>\\*?",

 end = "[-~]{1,2}>\\*?"
          ),
          Mode(
            begin = "(" +
 JS_IDENT_RE + "\\s*(?:=|:=)\\s*)?(\\(.*\\))?\\s*\\B!?[-~]{1,2}>\\*?",

 end = "!?[-~]{1,2}>\\*?"
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
        begin = JS_IDENT_RE + ":",

 end = ":",

        returnBegin = true,
 returnEnd = true,
        relevance = 0
      )
    ))
  );
}
