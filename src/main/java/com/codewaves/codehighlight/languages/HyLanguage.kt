package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Hy
Description = Hy syntax (based on clojure.js)
Author = Sergey Sobko <s.sobko@profitware.ru>
Category = lisp
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class HyLanguage : LanguageBuilder  {
  val keywords = listOf(
    Keyword(className = "builtin-name",

 value = // keywords
      "!= % %= & &= * ** **= *= *map + += ), --build-class-- --import-- -= . \"\"\" /\"\"\" //= /= < << <<= <= = > >= >> >>= @ @= ^ ^= abs accumulate all and any ap-compose ap-dotimes ap-each ap-each-while ap-filter ap-first ap-if ap-last ap-map ap-map-when ap-pipe ap-reduce ap-reject apply as-> ascii assert assoc bin break butlast callable calling-module-name car case cdr chain chr coll? combinations compile compress cond cons cons? continue count curry cut cycle dec def default-method defclass defmacro defmacro-alias defmacro/g! defmain defmethod defmulti defn defn-alias defnc defnr defreader defseq del delattr delete-route dict-comp dir disassemble dispatch-reader-macro distinct divmod do doto drop drop-last drop-while empty? end-sequence eval eval-and-compile eval-when-compile even? every? except exec filter first flatten float? fn fnc fnr for for* format fraction genexpr gensym get getattr global globals group-by hasattr hash hex id identity if if* if-not if-python2 import in inc input instance? integer integer-char? integer? interleave interpose is is-coll is-cons is-empty is-even is-every is-float is-instance is-integer is-integer-char is-iterable is-iterator is-keyword is-neg is-none is-not is-numeric is-odd is-pos is-string is-symbol is-zero isinstance islice issubclass iter iterable? iterate iterator? keyword keyword? lambda last len let lif lif-not list* list-comp locals loop macro-error macroexpand macroexpand-1 macroexpand-all map max merge-with method-decorator min multi-decorator multicombinations name neg? next none? nonlocal not not-in not? nth numeric? oct odd? open or ord partition permutations pos? post-route postwalk pow prewalk print product profile/calls profile/cpu put-route quasiquote quote raise range read read-str recursive-replace reduce remove repeat repeatedly repr require rest round route route-with-methods rwm second seq set-comp setattr setv some sorted string string? sum switch symbol? take take-nth take-while tee try unless unquote unquote-splicing vars walk when while with with* with-decorator with-gensyms xi xor yield yield-from zero? zip zip-longest | |= ~"
   );

  val SYMBOLSTART = "a-zA-Z_\\-!.?+*=<>&#\'";
  val SYMBOL_RE = "[" +
 SYMBOLSTART + "][" +
 SYMBOLSTART + "0-9/;:]*";
  val SIMPLE_NUMBER_RE = "[-+]?\\d+(\\.\\d+)?";

  val SHEBANG = Mode(
    className = "meta",

    begin = "^#!",

 end = "\$"
  );

  val SYMBOL = Mode(
    begin = SYMBOL_RE,
    relevance = 0
  );
  val NUMBER = Mode(
    className = "number",

 begin = SIMPLE_NUMBER_RE,
    relevance = 0
  );
  val STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null));
  val COMMENT = hljs.COMMENT(
    ";",

    "\$",

    Mode(
      relevance = 0
    )
  );
  val LITERAL = Mode(
    className = "literal",

    begin = """\b(listOf(Tt]rue|[Ff]alse|nil|None)\b"""
  );
  val COLLECTION = Mode(
    begin = "[\\[\\{]",

 end = "[\\]\\}]"
  );
  val HINT = Mode(
    className = "comment",

    begin = "\\^" +
 SYMBOL_RE
  );
  val HINT_COL = hljs.COMMENT("\\^\\{",
 "\\}");
  val KEY = Mode(
    className = "symbol",

    begin = "[:]{1,2}" +
 SYMBOL_RE
  );
  val LIST = Mode(
    begin = "\\(",

 end = "\\)"
  );
  val BODY = Mode(
    endsWithParent = true,
    relevance = 0
  );
  val NAME = Mode(
    keywords = keywords(keywords),
    lexemes = SYMBOL_RE,
    className = "name",

 begin = SYMBOL_RE,
    starts = BODY
  );
  val DEFAULT_CONTAINS = listOf(LIST, STRING, HINT, HINT_COL, COMMENT, KEY, COLLECTION, NUMBER, LITERAL, SYMBOL);

  LIST.contains = listOf(hljs.COMMENT("comment",
 "\"), NAME, BODY);
  BODY.contains = DEFAULT_CONTAINS;
  COLLECTION.contains = DEFAULT_CONTAINS;

  override fun build() = Mode(
    aliases = listOf("hylang"),
    illegal = """\S""",

    contains = listOf(SHEBANG, LIST, STRING, HINT, HINT_COL, COMMENT, KEY, COLLECTION, NUMBER, LITERAL)
  )
}
