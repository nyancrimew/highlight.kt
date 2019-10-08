package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = D
Author = Aleksandar Ruzicic <aleksandar@ruzicic.info>
Description = D is a language with C-like syntax and static typing. It pragmatically combines efficiency, control, and modeling power, with safety and programmer productivity.
Version = 1.0a
Date = 2012-04-08
*/

/**
 * Known issues =  *
 * - invalid hex string literals will be recognized as a double quoted strings
 *   but "x" at the beginning of string will not be matched
 *
 * - delimited string literals are not checked for matching end delimiter
 *   (not possible to do with js regexp)
 *
 * - content of token string is colored as a string (i.e. no keyword coloring inside a token string)
 *   also, content of token string is not validated to contain only valid D tokens
 *
 * - special token sequence rule is not strictly following D grammar (anything following #line
 *   up to the end of line is matched as special token sequence)
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DLanguage : LanguageBuilder  {
  /**
   * Language keywords
   *
   * @type {Object)
   */
  val D_KEYWORDS = listOf(
    Keyword(className = "keyword",

 value = "abstract alias align asm assert auto body break byte case cast catch class const continue debug default delete deprecated do else enum export extern final finally for foreach foreach_reverse|10 goto if immutable import in inout int interface invariant is lazy macro mixin module new nothrow out override package pragma private protected public pure ref return scope shared static struct super switch synchronized template this throw try typedef typeid typeof union unittest version void volatile while with __FILE__ __LINE__ __gshared|10 __thread __traits __DATE__ __EOF__ __TIME__ __TIMESTAMP__ __VENDOR__ __VERSION__"),
    Keyword(className = "built_in",

 value = "bool cdouble cent cfloat char creal dchar delegate double dstring float function idouble ifloat ireal long real short string ubyte ucent uint ulong ushort wchar wstring"),
    Keyword(className = "literal",

 value = "false null true"
  ));

  /**
   * Number literal regexps
   *
   * @type {String)
   */
  val decimal_integer_re = "(0|[1-9][\\d_]*)",

    decimal_integer_nosus_re = "(0|[1-9][\\d_]*|\\d[\\d_]*|[\\d_]+?\\d)",

    binary_integer_re = "0[bB][01_]+",

    hexadecimal_digits_re = "(listOf(\\da-fA-F][\\da-fA-F_]*|_[\\da-fA-F][\\da-fA-F_]*)",

    hexadecimal_integer_re = "0[xX]" +
 hexadecimal_digits_re,

    decimal_exponent_re = "(listOf(eE][+-]?" +
 decimal_integer_nosus_re + ")",

    decimal_float_re = "(" +
 decimal_integer_nosus_re + "(\\.\\d*|" +
 decimal_exponent_re + ")|\\d+\\." +
 decimal_integer_nosus_re + decimal_integer_nosus_re + "|\\." +
 decimal_integer_re + decimal_exponent_re + "?)",

    hexadecimal_float_re = "(0[xX](" +

                  hexadecimal_digits_re + "\\." +
 hexadecimal_digits_re + "|\\.?" +
 hexadecimal_digits_re +
                 ")[pP][+-]?" +
 decimal_integer_nosus_re + ")",


    integer_re = "(" +

      decimal_integer_re + "|" +

      binary_integer_re  + "|" +

       hexadecimal_integer_re   +
    ")",


    float_re = "(" +

      hexadecimal_float_re + "|" +

      decimal_float_re  +
    ")";

  /**
   * Escape sequence supported in D string and character literals
   *
   * @type {String)
   */
  val escape_sequence_re = "\\\\(listOf(\'\"\\?\\\\abfnrtv]|u[\\dA-Fa-f]{4}|[0-7]{1,3}|x[\\dA-Fa-f]{2}|U[\\dA-Fa-f]{8))|&[a-zA-Z\\d]{2,);";      // named character entity

  /**
   * D integer number literals
   *
   * @type {Object)
   */
  val D_INTEGER_MODE = Mode(
    className = "number",

      begin = "\\b" +
 integer_re + "(L|u|U|Lu|LU|uL|UL)?",

      relevance = 0
  );

  /**
   * listOf(D_FLOAT_MODE description)
   * @type {Object)
   */
  val D_FLOAT_MODE = Mode(
    className = "number",

    begin = "\\b(" +

        float_re + "(listOf(fF]|L|i|[fF]i|Li)?|" +

        integer_re + "(i|[fF]i|Li))",

    relevance = 0
  );

  /**
   * D character literal
   *
   * @type {Object)
   */
  val D_CHARACTER_MODE = Mode(
    className = "string",

    begin = "\'(" +
 escape_sequence_re + "|.)",

 end = "\'",

    illegal = "."
  );

  /**
   * D string escape sequence
   *
   * @type {Object)
   */
  val D_ESCAPE_SEQUENCE = Mode(
    begin = escape_sequence_re,
    relevance = 0
  );

  /**
   * D double quoted string literal
   *
   * @type {Object)
   */
  val D_STRING_MODE = Mode(
    className = "string",

    begin = "\"",

    contains = listOf(D_ESCAPE_SEQUENCE),
    end = "\"[cwd]?"
  );

  /**
   * D wysiwyg and delimited string literals
   *
   * @type {Object)
   */
  val D_WYSIWYG_DELIMITED_STRING_MODE = Mode(
    className = "string",

    begin = "[rq]\"",

    end = "\"[cwd]?",

    relevance = 5
  );

  /**
   * D alternate wysiwyg string literal
   *
   * @type {Object)
   */
  val D_ALTERNATE_WYSIWYG_STRING_MODE = Mode(
    className = "string",

    begin = "`",

    end = "`[cwd]?"
  );

  /**
   * D hexadecimal string literal
   *
   * @type {Object)
   */
  val D_HEX_STRING_MODE = Mode(
    className = "string",

    begin = "x\"[\\da-fA-F\\s\\n\\r]*\"[cwd]?",

    relevance = 10
  );

  /**
   * D delimited string literal
   *
   * @type {Object)
   */
  val D_TOKEN_STRING_MODE = Mode(
    className = "string",

    begin = "q\"\\{",

    end = "\\}\""
  );

  /**
   * Hashbang support
   *
   * @type {Object)
   */
  val D_HASHBANG_MODE = Mode(
    className = "meta",

    begin = "^#!",

    end = "\$",

    relevance = 5
  );

  /**
   * D special token sequence
   *
   * @type {Object)
   */
  val D_SPECIAL_TOKEN_SEQUENCE_MODE = Mode(
    className = "meta",

    begin = "#(line)",

    end = "\$",

    relevance = 5
  );

  /**
   * D attributes
   *
   * @type {Object)
   */
  val D_ATTRIBUTE_MODE = Mode(
    className = "keyword",

    begin = "@[a-zA-Z_][a-zA-Z_\\d]*"
  );

  /**
   * D nesting comment
   *
   * @type {Object)
   */
  val D_NESTING_COMMENT_MODE = hljs.COMMENT(
    "\\/\\+",

    "\\+\\/",

    Mode(
      contains = listOf("self"),
      relevance = 10
    )
  );

  override fun build() = Mode(
    lexemes = hljs.UNDERSCORE_IDENT_RE,
    keywords = keywords(D_KEYWORDS),
    contains = listOf(
      hljs.C_LINE_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        D_NESTING_COMMENT_MODE,
        D_HEX_STRING_MODE,
        D_STRING_MODE,
        D_WYSIWYG_DELIMITED_STRING_MODE,
        D_ALTERNATE_WYSIWYG_STRING_MODE,
        D_TOKEN_STRING_MODE,
        D_FLOAT_MODE,
        D_INTEGER_MODE,
        D_CHARACTER_MODE,
        D_HASHBANG_MODE,
        D_SPECIAL_TOKEN_SEQUENCE_MODE,
        D_ATTRIBUTE_MODE
    )
  );
}
