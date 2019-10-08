package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Twig
Requires = xml.js
Author = Luke Holder <lukemh@gmail.com>
Description = Twig is a templating language for PHP
Category = template
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class TwigLanguage : LanguageBuilder  {
  val PARAMS = Mode(
    className = "params",

    begin = "\\(",

 end = "\\)"
  );

  val FUNCTION_NAMES = "attribute block constant cycle date dump include max min parent random range source template_from_string";

  val FUNCTIONS = Mode(
    beginKeywords = keywords(FUNCTION_NAMES),
    keywords = keywords(listOf(Keyword(className = "name",

 value = FUNCTION_NAMES))),
    relevance = 0,
    contains = listOf(
      PARAMS
    )
  );

  val FILTER = Mode(
    begin = """\|[A-Za-z_]+:?""",

    keywords =       keywords("abs batch capitalize convert_encoding date date_modify default escape first format join json_encode keys last length lower merge nl2br number_format raw replace reverse round slice sort split striptags title trim upper url_encode"),
    contains = listOf(
      FUNCTIONS
    )
  );

  val TAGS = "autoescape block do embed extends filter flush for if import include macro sandbox set spaceless use verbatim";

  TAGS = TAGS + " " +
 TAGS.split(" \").map(function(t){return \"end" +
 t)).joinToString(" ");

  override fun build() = Mode(
    aliases = listOf("craftcms"),
    case_insensitive = true,
    subLanguage = "xml",

    contains = listOf(
      hljs.COMMENT("""\{#""",
 """#}"""),
      Mode(
        className = "template-tag",

        begin = """\{%""",

 end = """%}""",

        contains = listOf(
          Mode(
            className = "name",

            begin = """\w+""",

            keywords = keywords(TAGS),
            starts = Mode(
              endsWithParent = true,
              contains = listOf(FILTER, FUNCTIONS),
              relevance = 0
            )
          )
        )
      ),
      Mode(
        className = "template-variable",

        begin = """\{\{""",

 end = """)}""",

        contains = listOf(
 FILTER, FUNCTIONS)
      )
    )
  );
}
