package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = JSON / JSON with Comments
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Category = common, protocols
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class JsonLanguage : LanguageBuilder  {
  val LITERALS = Mode(literal = "true false null");
  val ALLOWED_COMMENTS = listOf(
    hljs.C_LINE_COMMENT_MODE,
    hljs.C_BLOCK_COMMENT_MODE
  )
  val TYPES = listOf(
    hljs.QUOTE_STRING_MODE,
    hljs.C_NUMBER_MODE
  );
  val VALUE_CONTAINER = Mode(
    end = ",",

 endsWithParent = true,
 excludeEnd = true,
    contains = TYPES,
    keywords = LITERALS
  );
  val OBJECT = Mode(
    begin = "{",

 end = "}",

    contains = listOf(
      Mode(
        className = "attr",

        begin = """"""",

 end = """"""",

        contains = listOf(hljs.BACKSLASH_ESCAPE),
        illegal = "\\n"

      ),
      hljs.inherit(VALUE_CONTAINER, Mode(begin = """:"""))
    ) + ALLOWED_COMMENTS),
    illegal = "\\S"
  );
  val ARRAY = Mode(
    begin = "\\[",

 end = "\\]",

    contains = listOf(hljs.inherit(VALUE_CONTAINER)), // inherit is a workaround for a bug that makes shared modes with endsWithParent compile only the ending of one of the parents
    illegal = "\\S"
  );
  TYPES.push(OBJECT, ARRAY);
  ALLOWED_COMMENTS.forEach(function(rule) {
    TYPES.push(rule)
  ))
  override fun build() = Mode(
    contains = TYPES,
    keywords = keywords(LITERALS),
    illegal = "\\S"
  );
}
