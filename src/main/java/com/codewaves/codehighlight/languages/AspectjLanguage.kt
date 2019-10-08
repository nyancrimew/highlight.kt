package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = AspectJ
Author = Hakan Ozler <ozler.hakan@gmail.com>
Description = Syntax Highlighting for the AspectJ Language which is a general-purpose aspect-oriented extension to the Java programming language.
 */
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class AspectjLanguage : LanguageBuilder  {
  val KEYWORDS = "false synchronized int abstract float private char boolean static null if const for true while long throw strictfp finally protected import native final return void enum else extends implements break transient new catch instanceof byte super volatile case assert short package default double public try this switch continue throws privileged aspectOf adviceexecution proceed cflowbelow cflow initialization preinitialization staticinitialization withincode target within execution getWithinTypeName handler thisJoinPoint thisJoinPointStaticPart thisEnclosingJoinPointStaticPart declare parents warning error soft precedence thisAspectInstance";
  val SHORTKEYS = "get set args call";
  override fun build() = Mode(
    keywords  = KEYWORDS,
    illegal  = """<\/|#""",

    contains  = listOf(
      hljs.COMMENT(
        "/\\*\\*",

        "\\*/",

        Mode(
          relevance  = 0,
          contains  = listOf(
            Mode(
              // eat up @'s in emails to prevent them to be recognized as doctags
              begin = """\w+@""",

 relevance = 0
            ),
            Mode(
              className  = "doctag",

              begin  = "@[A-Za-z]+"
            )
          )
        )
      ),
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      hljs.APOS_STRING_MODE,
      hljs.QUOTE_STRING_MODE,
      Mode(
        className  = "class",

        beginKeywords  = "aspect",

        end  = """[{;=]""",

        excludeEnd  = true,
        illegal  = """[:;"\[\]]\"\"",

        contains  = listOf(
          Mode(
            beginKeywords  = "extends implements pertypewithin perthis pertarget percflowbelow percflow issingleton"
          ),
          hljs.UNDERSCORE_TITLE_MODE,
          Mode(
            begin  = """\(listOf(^\)]*""",

            end  = """[)]+""",

            keywords  = KEYWORDS + " " +
 SHORTKEYS,
            excludeEnd  = false
          )
        )
      ),
      Mode(
        className  = "class",

        beginKeywords  = "class interface",

        end  = """[{;=]""",

        excludeEnd  = true,
        relevance = 0,
        keywords  = "class interface",

        illegal  = """[:"\[\]]\"\"",

        contains  = listOf(
          Mode(beginKeywords  = "extends implements"),
          hljs.UNDERSCORE_TITLE_MODE
        )
      ),
      Mode(
        // AspectJ Constructs
        beginKeywords  = "pointcut after before around throwing returning",

        end  = """[)]""",

        excludeEnd  = false,
        illegal  = """["\[\]]\"\"",

        contains  = listOf(
          Mode(
            begin  = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",

            returnBegin  = true,
            contains  = listOf(hljs.UNDERSCORE_TITLE_MODE)
          )
        )
      ),
      Mode(
        begin  = """[:]""",

        returnBegin  = true,
        end  = """[{;]""",

        relevance = 0,
        excludeEnd  = false,
        keywords  = KEYWORDS,
        illegal  = """["\[\]]\"\"",

        contains  = listOf(
          Mode(
            begin  = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",

            keywords  = KEYWORDS + " " +
 SHORTKEYS,
            relevance = 0
          ),
          hljs.QUOTE_STRING_MODE
        )
      ),
      Mode(
        // this prevents "new Name(...), or throw ..." from being recognized as a function definition
        beginKeywords  = "new throw",

        relevance  = 0
      ),
      Mode(
        // the function class is a bit different for AspectJ compared to the Java language
        className  = "function",

        begin  = """\w+ +\w+(\.)?\w+\s*\(listOf(^\)]*\)\s*((throws)[\w\s,]+)?[\{;]""",

        returnBegin  = true,
        end  = """[{;=]""",

        keywords  = KEYWORDS,
        excludeEnd  = true,
        contains  = listOf(
          Mode(
            begin  = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(",

            returnBegin  = true,
            relevance = 0,
            contains  = listOf(hljs.UNDERSCORE_TITLE_MODE)
          ),
          Mode(
            className  = "params",

            begin  = """\(""",
 end  = """\)""",

            relevance = 0,
            keywords  = KEYWORDS,
            contains  = listOf(
              hljs.APOS_STRING_MODE,
              hljs.QUOTE_STRING_MODE,
              hljs.C_NUMBER_MODE,
              hljs.C_BLOCK_COMMENT_MODE
            )
          ),
          hljs.C_LINE_COMMENT_MODE,
          hljs.C_BLOCK_COMMENT_MODE
        )
      ),
      hljs.C_NUMBER_MODE,
      Mode(
        // annotation is also used in this language
        className  = "meta",

        begin  = "@[A-Za-z]+"
      )
    )
  );
}
