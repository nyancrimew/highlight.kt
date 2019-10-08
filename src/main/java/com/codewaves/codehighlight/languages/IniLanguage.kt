package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Ini, TOML
Contributors = Guillaume Gomez <guillaume1.gomez@gmail.com>
Category = common, config
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class IniLanguage : LanguageBuilder  {
  val STRING = Mode(
    className = "string",

    contains = listOf(hljs.BACKSLASH_ESCAPE),
    variants = listOf(
      Mode(
        begin = "'''",

 end = "'''",

        relevance = 10
      ), Mode(
        begin = "\"\"\"",

 end = "\"\"\"",

        relevance = 10
      ), Mode(
        begin = "\"",

 end = "\""
      ), Mode(
        begin = "'",

 end = "'"
      )
    )
  );
  override fun build() = Mode(
    aliases = listOf("toml"),
    case_insensitive = true,
    illegal = """\S""",

    contains = listOf(
      hljs.COMMENT(";",
 "\$"),
      hljs.HASH_COMMENT_MODE,
      Mode(
        className = "section",

        begin = """^\s*\[+""",

 end = """\]+"""
      ),
      Mode(
        begin = """^[a-z0-9\[\]_\.-]+\s*=\s*""",

 end = "\$",

        returnBegin = true,
        contains = listOf(
          Mode(
            className = "attr",

            begin = """[a-z0-9\[\]_\.-]+"""
          ),
          Mode(
            begin = """=""",

 endsWithParent = true,
            relevance = 0,
            contains = listOf(
              hljs.COMMENT(";",
 "\$"),
              hljs.HASH_COMMENT_MODE,
              Mode(
                className = "literal",

                begin = """\bon|off|true|false|yes|no\b"""
              ),
              Mode(
                className = "variable",

                variants = listOf(
                  Mode(begin = """\$[\w\d"][\w\d_]*\"\""),
                  Mode(begin = """\${'$'}\{(.*?)}""")
                )
              ),
              STRING,
              Mode(
                className = "number",

                begin = """(listOf(\+\-]+)?[\d]+_[\d_]+"""
              ),
              hljs.NUMBER_MODE
            )
          )
        )
      )
    )
  );
}
