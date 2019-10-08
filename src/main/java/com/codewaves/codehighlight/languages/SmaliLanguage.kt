package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Smali
Author = Dennis Titze <dennis.titze@gmail.com>
Description = Basic Smali highlighting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class SmaliLanguage : LanguageBuilder  {
  val smali_instr_low_prio = listOf("add",
 "and",
 "cmp",
 "cmpg",
 "cmpl",
 "const",
 "div",
 "double",
 "float",
 "goto",
 "if",
 "int",
 "long",
 "move",
 "mul",
 "neg",
 "new",
 "nop",
 "not",
 "or",
 "rem",
 "return",
 "shl",
 "shr",
 "sput",
 "sub",
 "throw",
 "ushr",
 "xor");
  val smali_instr_high_prio = listOf("aget",
 "aput",
 "array",
 "check",
 "execute",
 "fill",
 "filled",
 "goto/16",
 "goto/32",
 "iget",
 "instance",
 "invoke",
 "iput",
 "monitor",
 "packed",
 "sget",
 "sparse");
  val smali_keywords = keywords(listOf("transient",
 "constructor",
 "abstract",
 "final",
 "synthetic",
 "public",
 "private",
 "protected",
 "static",
 "bridge",
 "system"));
  override fun build() = Mode(
    aliases = listOf("smali"),
    contains = listOf(
      Mode(
        className = "string",

        begin = "\"",

 end = "\"",

        relevance = 0
      ),
      hljs.COMMENT(
        "#",

        "\$",

        Mode(
          relevance = 0
        )
      ),
      Mode(
        className = "keyword",

        variants = listOf(
          Mode(begin = "\\s*\\.end\\s[a-zA-Z0-9]*"),
          Mode(begin = "^[ ]*\\.[a-zA-Z]*",

 relevance = 0),
          Mode(begin = "\\s:[a-zA-Z_0-9]*",

 relevance = 0),
          Mode(begin = "\\s(" +
 smali_keywords.joinToString("|\") + \")")
        )
      ),
      Mode(
        className = "built_in",

        variants  = listOf(
          Mode(
            begin = "\\s(\"+smali_instr_low_prio.joinToString(\"|\")+\")\\s"
          ),
          Mode(
            begin = "\\s(\"+smali_instr_low_prio.joinToString(\"|\")+\")((\\-|/)[a-zA-Z0-9]+)+\\s",

            relevance = 10
          ),
          Mode(
            begin = "\\s(\"+smali_instr_high_prio.joinToString(\"|\")+\")((\\-|/)[a-zA-Z0-9]+)*\\s",

            relevance = 10
          )
        )
      ),
      Mode(
        className = "class",

        begin = "L[^\(;:\n]*;",

        relevance = 0
      ),
      Mode(
        begin = "[vp][0-9]+"

      )
    )
  );
}
