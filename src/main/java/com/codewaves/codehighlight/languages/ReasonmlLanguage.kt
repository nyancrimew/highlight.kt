package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = ReasonML
Author = Gidi Meir Morris <oss@gidi.io>
Category = functional
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ReasonmlLanguage : LanguageBuilder  {
  function orReValues(ops){
    return ops
    .map(function(op) {
      return op
        .split("")
        .map(function(char) {
          return "\\" +
 char;
        ))
        .joinToString("");
    ))
    .joinToString("|");
  )

  val RE_IDENT = "~?[a-z\$_][0-9a-zA-Z\$_]*";
  val RE_MODULE_IDENT = "`?[A-Z\$_][0-9a-zA-Z\$_]*";

  val RE_PARAM_TYPEPARAM = "\'?[a-z\$_][0-9a-z\$_]*";
  val RE_PARAM_TYPE = "\s*:\s*[a-z\$_][0-9a-z\$_]*(\(\s*(" +
 RE_PARAM_TYPEPARAM + "\s*(," +
 RE_PARAM_TYPEPARAM + ")*)?\s*\))?";
  val RE_PARAM = RE_IDENT + "(" +
 RE_PARAM_TYPE + ")?(" +
 RE_PARAM_TYPE + ")?";
  val RE_OPERATOR = "(" +
 orReValues(listOf("||",
 "&&",
 "++",
 "**",
 "+.",
 "*",
 "/",
 "*.",
 "/.",
 "...",
 "|>\")) + \"|==|===)";
  val RE_OPERATOR_SPACED = "\\s+" +
 RE_OPERATOR + "\\s+";

  val KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "and as asr assert begin class constraint do done downto else end exception externalfor fun function functor if in include inherit initializerland lazy let lor lsl lsr lxor match method mod module mutable new nonrecobject of open or private rec sig struct then to try type val virtual when while with"),
    Keyword(className = "built_in",

 value = "array bool bytes char exn|5 float int int32 int64 list lazy_t|5 nativeint|5 ref string unit "),
    Keyword(className = "literal",

 value = "true false"
  ));

  val RE_NUMBER = "\\b(0[xX][a-fA-F0-9_]+[Lln]?|0[oO][0-7_]+[Lln]?|0[bB][01_]+[Lln]?|[0-9][0-9_]*(listOf(Lln]|(\\.[0-9_]*)?(listOf(eE][-+]?[0-9_]+)?)?)";

  val NUMBER_MODE = Mode(
    className = "number",

    relevance = 0,
    variants = listOf(
      Mode(
        begin = RE_NUMBER
      ),
      Mode(
        begin = "\\(\\-" +
 RE_NUMBER + "\\)"
      )
    )
  );

  val OPERATOR_MODE = Mode(
    className = "operator",

    relevance = 0,
    begin = RE_OPERATOR
  );
  val LIST_CONTENTS_MODES = listOf(
    Mode(
      className = "identifier",

      relevance = 0,
      begin = RE_IDENT
    ),
    OPERATOR_MODE,
    NUMBER_MODE
  );

  val MODULE_ACCESS_CONTENTS = listOf(
    hljs.QUOTE_STRING_MODE,
    OPERATOR_MODE,
    Mode(
      className = "module",

      begin = "\\b" +
 RE_MODULE_IDENT,
 returnBegin = true,
      end = "\.",

      contains = listOf(
        Mode(
          className = "identifier",

          begin = RE_MODULE_IDENT,
          relevance = 0
        )
      )
    )
  );

  val PARAMS_CONTENTS = listOf(
    Mode(
      className = "module",

      begin = "\\b" +
 RE_MODULE_IDENT,
 returnBegin = true,
      end = "\.",

      relevance = 0,
      contains = listOf(
        Mode(
          className = "identifier",

          begin = RE_MODULE_IDENT,
          relevance = 0
        )
      )
    )
  );

  val PARAMS_MODE = Mode(
    begin = RE_IDENT,
    end = "(,|\\n|\\))",

    relevance = 0,
    contains = listOf(
      OPERATOR_MODE,
      Mode(
        className = "typing",

        begin = ":",

        end = "(,|\\n)",

        returnBegin = true,
        relevance = 0,
        contains = PARAMS_CONTENTS
      )
    )
  );

  val FUNCTION_BLOCK_MODE = Mode(
    className = "function",

    relevance = 0,
    keywords = keywords(KEYWORDS),
    variants = listOf(
      Mode(
        begin = "\\s(\\(\\.?.*?\\)|" +
 RE_IDENT + ")\\s*=>",

        end = "\\s*=>",

        returnBegin = true,
        relevance = 0,
        contains = listOf(
          Mode(
            className = "params",

            variants = listOf(
              Mode(
                begin = RE_IDENT
              ),
              Mode(
                begin = RE_PARAM
              ),
              Mode(
                begin = """\(\s*\)"""

              )
            )
          )
        )
      ),
      Mode(
        begin = "\\s\\(\\.?[^;\\|]*\\)\\s*=>",

        end = "\\s=>",

        returnBegin = true,
        relevance = 0,
        contains = listOf(
          Mode(
            className = "params",

            relevance = 0,
            variants = listOf(
              PARAMS_MODE
            )
          )
        )
      ),
      Mode(
        begin = "\\(\\.\\s" +
 RE_IDENT + "\\)\\s*=>"
      )
    )
  );
  MODULE_ACCESS_CONTENTS.push(FUNCTION_BLOCK_MODE);

  val CONSTRUCTOR_MODE = Mode(
    className = "constructor",

    begin = RE_MODULE_IDENT + "\\(",

    end = "\\)",

    illegal = "\\n",

    keywords = keywords(KEYWORDS),
    contains = listOf(
      hljs.QUOTE_STRING_MODE,
      OPERATOR_MODE,
      Mode(
        className = "params",

        begin = "\\b" +
 RE_IDENT
      )
    )
  );

  val PATTERN_MATCH_BLOCK_MODE = Mode(
    className = "pattern-match",

    begin = "\\|",

    returnBegin = true,
    keywords = keywords(KEYWORDS),
    end = "=>",

    relevance = 0,
    contains = listOf(
      CONSTRUCTOR_MODE,
      OPERATOR_MODE,
      Mode(
        relevance = 0,
        className = "constructor",

        begin = RE_MODULE_IDENT
      )
    )
  );

  val MODULE_ACCESS_MODE = Mode(
    className = "module-access",

    keywords = keywords(KEYWORDS),
    returnBegin = true,
    variants = listOf(
      Mode(
        begin = "\\b(" +
 RE_MODULE_IDENT + "\\.)+" +
 RE_IDENT
      ),
      Mode(
        begin = "\\b(" +
 RE_MODULE_IDENT + "\\.)+\\(",

        end = "\\)",

        returnBegin = true,
        contains = listOf(
          FUNCTION_BLOCK_MODE,
          Mode(
            begin = "\\(",

            end = "\\)",

            skip = true
          )
        ) + MODULE_ACCESS_CONTENTS)
      ),
      Mode(
        begin = "\\b(" +
 RE_MODULE_IDENT + "\\.)+{",

        end = "}"
      )
    ),
    contains = MODULE_ACCESS_CONTENTS
  );

  PARAMS_CONTENTS.push(MODULE_ACCESS_MODE);

  override fun build() = Mode(
    aliases = listOf("re"),
    keywords = keywords(KEYWORDS),
    illegal = "(:\\-|:=|\\${|\\+=)",

    contains = listOf(
      hljs.COMMENT("/\\*",
 "\\*/",
 Mode( illegal = "^(\\#,\\/\\/)" )),
      Mode(
        className = "character",

        begin = "\'(\\\\[^\"]+|[^\"))\'",

        illegal = "\\n",

        relevance = 0
      ),
      hljs.QUOTE_STRING_MODE,
      Mode(
        className = "literal",

        begin = "\\(\\)",

        relevance = 0
      ),
      Mode(
        className = "literal",

        begin = "\\[\\|",

        end = "\\|\\]",

        relevance =  0,
        contains = LIST_CONTENTS_MODES
      ),
      Mode(
        className = "literal",

        begin = "\\[",

        end = "\\]",

        relevance = 0,
        contains = LIST_CONTENTS_MODES
      ),
      CONSTRUCTOR_MODE,
      Mode(
        className = "operator",

        begin = RE_OPERATOR_SPACED,
        illegal = "\\-\\->",

        relevance = 0
      ),
      NUMBER_MODE,
      hljs.C_LINE_COMMENT_MODE,
      PATTERN_MATCH_BLOCK_MODE,
      FUNCTION_BLOCK_MODE,
      Mode(
        className = "module-def",

        begin = "\\bmodule\\s+" +
 RE_IDENT + "\\s+" +
 RE_MODULE_IDENT + "\\s+=\\s+{",

        end = "}",

        returnBegin = true,
        keywords = keywords(KEYWORDS),
        relevance = 0,
        contains = listOf(
          Mode(
            className = "module",

            relevance = 0,
            begin = RE_MODULE_IDENT
          ),
          Mode(
            begin = "{",

            end = "}",

            skip = true
          )
        ) + MODULE_ACCESS_CONTENTS)
      ),
      MODULE_ACCESS_MODE
    )
  );
}
