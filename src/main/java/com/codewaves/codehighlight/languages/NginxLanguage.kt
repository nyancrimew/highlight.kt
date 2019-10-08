package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Nginx
Author = Peter Leonov <gojpeg@yandex.ru>
Contributors = Ivan Sagalaev <maniac@softwaremaniacs.org>
Category = common, config
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class NginxLanguage : LanguageBuilder  {
  val VAR = Mode(
    className = "variable",

    variants = listOf(
      Mode(begin = """\${'$'}\d+"""),
      Mode(begin = """\${'$'}\{""",

 end = """}"""),
      Mode(begin = "[\\$\\@]" +
 hljs.UNDERSCORE_IDENT_RE)
    )
  );
  val DEFAULT = Mode(
    endsWithParent = true,
    lexemes = "[a-z/_]+",

    keywords = listOf(
      Keyword(className = "literal",

 value = "on off yes no true false none blocked debug info notice warn error crit select break last permanent redirect kqueue rtsig epoll poll /dev/poll"
    )),
    relevance = 0,
    illegal = "=>",

    contains = listOf(
      hljs.HASH_COMMENT_MODE,
      Mode(
        className = "string",

        contains = listOf(hljs.BACKSLASH_ESCAPE, VAR),
        variants = listOf(
          Mode(begin = """"""",

 end = """""""),
          Mode(begin = """'""",

 end = """'""")
        )
      ),
      // this swallows entire URLs to avoid detecting numbers within
      Mode(
        begin = "(listOf(a-z]+):/",

 end = "\\s",

 endsWithParent = true,
 excludeEnd = true,
        contains = listOf(VAR)
      ),
      Mode(
        className = "regexp",

        contains = listOf(hljs.BACKSLASH_ESCAPE, VAR),
        variants = listOf(
          Mode(begin = "\\s\\^",

 end = "\\s|{|;",

 returnEnd = true)
          // regexp locations (~, ~*)
          Mode(begin = "~\\*?\\s+",

 end = "\\s|{|;",

 returnEnd = true),
          // *.example.com
          Mode(begin = "\\*(\\.[a-z\\-]+)+"),
          // sub.example.*
          Mode(begin = "(listOf(a-z\\-]+\\.)+\\*")
        )
      ),
      // IP
      Mode(
        className = "number",

        begin = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{1,5))?\\b"
      ),
      // units
      Mode(
        className = "number",

        begin = "\\b\\d+[kKmMgGdshdwy]*\\b",

        relevance = 0
      ),
      VAR
    )
  );

  override fun build() = Mode(
    aliases = listOf("nginxconf"),
    contains = listOf(
      hljs.HASH_COMMENT_MODE,
      Mode(
        begin = hljs.UNDERSCORE_IDENT_RE + "\\s+{",

 returnBegin = true,
        end = "{",

        contains = listOf(
          Mode(
            className = "section",

            begin = hljs.UNDERSCORE_IDENT_RE
          )
        ),
        relevance = 0
      ),
      Mode(
        begin = hljs.UNDERSCORE_IDENT_RE + "\\s",

 end = ";|{",

 returnBegin = true,
        contains = listOf(
          Mode(
            className = "attribute",

            begin = hljs.UNDERSCORE_IDENT_RE,
            starts = DEFAULT
          )
        ),
        relevance = 0
      )
    ),
    illegal = "[^\\s\\}]"
  );
}
