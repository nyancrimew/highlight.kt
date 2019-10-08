package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Leaf
Author = Hale Chan <halechan@qq.com>
Description = Based on the Leaf reference from https://vapor.github.io/documentation/guide/leaf.html.
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class LeafLanguage : LanguageBuilder  {
  override fun build() = Mode(
    contains = listOf(
      Mode(
        className = "function",

        begin = "#+[A-Za-z_0-9]*\\(",

        end:" {",

        returnBegin = true,
        excludeEnd = true,
        contains  = listOf(
          Mode(
            className = "keyword",

            begin = "#+"
          ),
          Mode(
            className = "title",

            begin = "[A-Za-z_][A-Za-z_0-9]*"
          ),
          Mode(
            className = "params",

            begin = "\\(",

 end = "\\)",

            endsParent = true,
            contains = listOf(
              Mode(
                className = "string",

                begin = "\"",

                end = "\""
              ),
              Mode(
                className = "variable",

                begin = "[A-Za-z_][A-Za-z_0-9]*"
              )
            )
          )
        )
      )
    )
  );
}
