package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Handlebars
Requires = xml.js
Author = Robin Ward <robin.ward@gmail.com>
Description = Matcher for Handlebars as well as EmberJS additions.
Category = template
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class HandlebarsLanguage : LanguageBuilder  {
  val BUILT_INS = Mode('builtin-name': "each in with if else unless bindattr action collection debugger log outlet template unbound view yield");
  override fun build() = Mode(
    aliases = listOf("hbs",
 "html.hbs",
 "html.handlebars"),
    case_insensitive = true,
    subLanguage = "xml",

    contains = listOf(
    hljs.COMMENT("{{!(--)?",
 "(--)?)}"),
      Mode(
        className = "template-tag",

        begin = """\{\{[#\/]""",

 end = """\}\}""",

        contains = listOf(
          Mode(
            className = "name",

            begin = """[a-zA-Z\.-]+""",

            keywords = keywords(BUILT_INS),
            starts = Mode(
              endsWithParent = true,
 relevance = 0,
              contains = listOf(
                hljs.QUOTE_STRING_MODE
              )
            )
          )
        )
      ),
      Mode(
        className = "template-variable",

        begin = """\{\{""",

 end = """\}\}""",

        keywords = BUILT_INS
      )
    )
  );
}
