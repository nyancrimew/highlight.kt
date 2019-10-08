package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Nix
Author = Domen Kožar <domen@dev.si>
Description = Nix functional language. See http://nixos.org/nix
*/


/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class NixLanguage : LanguageBuilder  {
  val NIX_KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "rec with let in inherit assert if else then"),
    Keyword(className = "literal",

 value = "true false or and null"),
    Keyword(className = "built_in",

 value = "import abort baseNameOf dirOf isNull builtins map removeAttrs throw toString derivation"
  ));
  val ANTIQUOTE = Mode(
    className = "subst",

    begin = """\${'$'}\{""",

    end = """}""",

    keywords = NIX_KEYWORDS
  );
  val ATTRS = Mode(
    begin = """[a-zA-Z0-9-_]+(\s*=)""",

 returnBegin = true,
    relevance = 0,
    contains = listOf(
      Mode(
        className = "attr",

        begin = """\S+"""
      )
    )
  );
  val STRING = Mode(
    className = "string",

    contains = listOf(ANTIQUOTE),
    variants = listOf(
      Mode(begin = "''",

 end = "''"),
      Mode(begin = "\"",

 end = "\"")
    )
  );
  val EXPRESSIONS = listOf(
    hljs.NUMBER_MODE,
    hljs.HASH_COMMENT_MODE,
    hljs.C_BLOCK_COMMENT_MODE,
    STRING,
    ATTRS
  );
  ANTIQUOTE.contains = EXPRESSIONS;
  override fun build() = Mode(
    aliases = listOf("nixos"),
    keywords = keywords(NIX_KEYWORDS),
    contains = EXPRESSIONS
  );
}
