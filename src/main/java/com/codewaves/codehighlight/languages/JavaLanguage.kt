package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.Keyword
import com.codewaves.codehighlight.core.keywords

class JavaLanguage : LanguageBuilder {
    companion object {
        val JAVA_IDENT_RE = "[\u00C0-\u02B8a-zA-Z_$][\u00C0-\u02B8a-zA-Z_$0-9]*"
        val GENERIC_IDENT_RE = JAVA_IDENT_RE + "(<" + JAVA_IDENT_RE + "(\\s*,\\s*" + JAVA_IDENT_RE + ")*>)?"
        val KEYWORDS = keywords("false synchronized int abstract float private char boolean var static null if const " +
                "for true while long strictfp finally protected import native final void " +
                "enum else break transient catch instanceof byte super volatile case assert short " +
                "package default double public try this switch continue throws protected public private " +
                "module requires exports do")
      
        // https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html
        val JAVA_NUMBER_RE = "\\b" +
        "(" +
        "0[bB]([01]+[01_]+[01]+|[01]+)" + // 0b...
        "|" +
        "0[xX]([a-fA-F0-9]+[a-fA-F0-9_]+[a-fA-F0-9]+|[a-fA-F0-9]+)" + // 0x...
        "|" +
        "(" +
        "([\\d]+[\\d_]+[\\d]+|[\\d]+)(\\.([\\d]+[\\d_]+[\\d]+|[\\d]+))?" +
        "|" +
        "\\.([\\d]+[\\d_]+[\\d]+|[\\d]+)" +
        ")" +
        "([eE][-+]?\\d+)?" + // octal, decimal, float
        ")" +
        "[lLfF]?"
        val JAVA_NUMBER_MODE = Mode(
          className = "number",
          begin = JAVA_NUMBER_RE,
          relevance = 0
        )
    }
    override fun build() = Mode(
        aliases = listOf("jsp"),
    keywords = KEYWORDS,
    illegal = "<\\/|#",
    contains = listOf(
      hljs.COMMENT(
        "/\\*\\*",
        "\\*/",
        Mode(
          relevance = 0,
          contains = listOf(
            Mode(
              // eat up @'s in emails to prevent them to be recognized as doctags
              begin = "\\w+@",
            relevance = 0
            ),
            Mode(
              className = "doctag",
              begin = "@[A-Za-z]+"
            )
          )
        )
      ),
      hljs.C_LINE_COMMENT_MODE,
      hljs.C_BLOCK_COMMENT_MODE,
      hljs.APOS_STRING_MODE,
      hljs.QUOTE_STRING_MODE,
      Mode(
        className = "class",
        beginKeywords = keywords("class interface"),
        end = "[{;=]",
        excludeEnd = true,
        keywords = keywords("class interface"),
        illegal = "[:\"\\[\\]]",
        contains= listOf(
          Mode(beginKeywords = keywords("extends implements")),
          hljs.UNDERSCORE_TITLE_MODE
        )
      ),
      Mode(
        // Expression keywords prevent 'keyword Name(...)' from being
        // recognized as a function definition
        beginKeywords = keywords("new throw return else"),
        relevance = 0
      ),
      Mode(
        className = "function",
        begin = "(" + GENERIC_IDENT_RE + "\\s+)+" + hljs.UNDERSCORE_IDENT_RE + "\\s*\\(", 
        returnBegin = true,
         end = "[{;=]",
        excludeEnd = true,
        keywords = KEYWORDS,
        contains = listOf(
          Mode(
            begin = hljs.UNDERSCORE_IDENT_RE + "\\s*\\(", 
            returnBegin = true,
            relevance = 0,
            contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
          ),
          Mode(
            className = "params",
            begin = "\\(",
            end = "\\)",
            keywords = KEYWORDS,
            relevance = 0,
            contains = listOf(
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
      JAVA_NUMBER_MODE,
      Mode(
        className = "meta",
         begin = "@[A-Za-z]+"
      )
    )
    )
}