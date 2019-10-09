package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.Keyword
import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.hljs
import com.codewaves.codehighlight.core.keywords

/*
Language = C++
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Contributors = Evgeny Stepanischev <imbolk@gmail.com>, Zaven Muradyan <megalivoithos@gmail.com>, Roel Deckers <admin@codingcat.nl>, Sam Wu <samsam2310@gmail.com>, Jordi Petit <jordi.petit@gmail.com>, Pieter Vantorre <pietervantorre@gmail.com>, Google Inc. (David Benjamin) <davidben@google.com>
Category = common, system
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/cpp.js MD5 <49ca5457a543a3a99247661fc33540eb>
 */
internal fun cpp(): Mode {
    var CPP_PRIMITIVE_TYPES = Mode(
        className = "keyword",
        begin = "\\b[a-z\\d_]*_t\\b"
    )
    // https://en.cppreference.com/w/cpp/language/escape
    // \\ \x \xFF \u2837 \u00323747 \374
    var CHARACTER_ESCAPES = "\\\\(x[0-9A-Fa-f]{2}|u[0-9A-Fa-f]{4,8}|[0-7]{3}|\\S)"
    var STRINGS = Mode(
        className = "string",
        variants = listOf(
            Mode(
                begin = "(u8?|U|L)?\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                begin = "(u8?|U|L)?'(" +
                    CHARACTER_ESCAPES + "|.)",
                end = "'",
                illegal = "."
            ),
            Mode(
                begin =
                    """(?:u8?|U|L)?R"([^()\\ ]{0,16))\((?:.|\n)*?\)\1""""
            )
        )
    )
    var NUMBERS = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0b[01\"]+)"),
            Mode(begin = "(-?)\\b([\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(u|U|l|L|ul|UL|f|F|b|B)"),
            Mode(begin = "(-?)(\\b0[xX][a-fA-F0-9\"]+|(\\b[\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)([eE][-+]?[\\d\"]+)?)")
        ),
        relevance = 0
    )
    var PREPROCESSOR = Mode(
        className = "meta",
        begin =
            """#\s*[a-z]+\b""",
        end =
            """${'$'}""",
        keywords = listOf(
            Keyword(
                className = "meta-keyword",
                value =
                    "if else elif endif define undef warning error line pragma ifdef ifndef include"
            )
        ),
        contains = listOf(
            Mode(
                begin =
                    """\\\n""",
                relevance = 0
            ),
            hljs.inherit(STRINGS, Mode(className = "meta-string")),
            Mode(
                className = "meta-string",
                begin =
                    """<[^\n>]*>""",
                end =
                    """${'$'}""",
                illegal = "\\n"
            ),
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE
        )
    )
    var FUNCTION_TITLE = hljs.IDENT_RE + "\\s*\\("
    var CPP_KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value = "int float while private char catch import module export virtual operator sizeof dynamic_cast|10 typedef const_cast|10 const for static_cast|10 union namespace unsigned long volatile static protected bool template mutable if public friend do goto auto void enum else break extern using asm case typeid short reinterpret_cast|10 default double register explicit signed typename try this switch continue inline delete alignof constexpr consteval constinit decltype concept co_await co_return co_yield requires noexcept static_assert thread_local restrict _Bool complex _Complex _Imaginary atomic_bool atomic_char atomic_schar atomic_uchar atomic_short atomic_ushort atomic_int atomic_uint atomic_long atomic_ulong atomic_llong atomic_ullong new throw return and or not"
        ),
        Keyword(
            className = "built_in",
            value = "std string wstring cin cout cerr clog stdin stdout stderr stringstream istringstream ostringstream auto_ptr deque list queue stack vector map set bitset multiset multimap unordered_set unordered_map unordered_multiset unordered_multimap array shared_ptr abort abs acos asin atan2 atan calloc ceil cosh cos exit exp fabs floor fmod fprintf fputs free frexp fscanf isalnum isalpha iscntrl isdigit isgraph islower isprint ispunct isspace isupper isxdigit tolower toupper labs ldexp log10 log malloc realloc memchr memcmp memcpy memset modf pow printf putchar puts scanf sinh sin snprintf sprintf sqrt sscanf strcat strchr strcmp strcpy strcspn strlen strncat strncmp strncpy strpbrk strrchr strspn strstr tanh tan vfprintf vprintf vsprintf endl initializer_list unique_ptr"
        ),
        Keyword(
            className = "literal",
            value = "true false nullptr NULL"
        )
    )
    var EXPRESSION_CONTAINS = listOf(
        CPP_PRIMITIVE_TYPES,
        hljs.C_LINE_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        NUMBERS,
        STRINGS
    )
    return Mode(
        aliases = listOf(
            "c",
            "cc",
            "h",
            "c++",
            "h++",
            "hpp",
            "hh",
            "hxx",
            "cxx"
        ),
        keywords = keywords(CPP_KEYWORDS),
        illegal = "</",
        contains = EXPRESSION_CONTAINS + listOf(
            PREPROCESSOR,
            Mode(
                begin = "\\b(deque|list|queue|stack|vector|map|set|bitset|multiset|multimap|unordered_map|unordered_set|unordered_multiset|unordered_multimap|array\\s*<",
                end = ">",
                keywords = keywords(CPP_KEYWORDS),
                contains = listOf(
                    hljs.SELF,
                    CPP_PRIMITIVE_TYPES
                )
            ),
            Mode(
                begin = hljs.IDENT_RE + "::",
                keywords = keywords(CPP_KEYWORDS)
            ),
            Mode(
                // This mode covers expression context where we can't expect a function
                // definition and shouldn't highlight anything that looks like one:
                // `return some()`, `else if()`, `(x*sum(1, 2))`
                variants = listOf(
                    Mode(
                        begin =
                            """=""",
                        end =
                            """;"""
                    ),
                    Mode(
                        begin =
                            """\(""",
                        end =
                            """\)"""
                    ),
                    Mode(
                        beginKeywords = keywords("new throw return else"),
                        end =
                            """;"""
                    )
                ),
                keywords = keywords(CPP_KEYWORDS),
                contains = EXPRESSION_CONTAINS + listOf(
                    Mode(
                        begin =
                            """\(""",
                        end =
                            """\""",
                        keywords = keywords(CPP_KEYWORDS),
                        contains = EXPRESSION_CONTAINS + listOf(hljs.SELF),
                        relevance = 0
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "function",
                begin = "(" +
                    hljs.IDENT_RE + "[\\*&\\s]+)+" +
                    FUNCTION_TITLE,
                returnBegin = true,
                end =
                    """[{;=]""",
                excludeEnd = true,
                keywords = keywords(CPP_KEYWORDS),
                illegal =
                    """[^\w\s\*&]""",
                contains = listOf(
                    Mode(
                        begin = FUNCTION_TITLE,
                        returnBegin = true,
                        contains = listOf(hljs.TITLE_MODE),
                        relevance = 0
                    ),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        keywords = keywords(CPP_KEYWORDS),
                        relevance = 0,
                        contains = listOf(
                            hljs.C_LINE_COMMENT_MODE,
                            hljs.C_BLOCK_COMMENT_MODE,
                            STRINGS,
                            NUMBERS,
                            CPP_PRIMITIVE_TYPES,
                            // Count matching parentheses.
                            Mode(
                                begin =
                                    """\(""",
                                end =
                                    """\)""",
                                keywords = keywords(CPP_KEYWORDS),
                                relevance = 0,
                                contains = listOf(
                                    hljs.SELF,
                                    hljs.C_LINE_COMMENT_MODE,
                                    hljs.C_BLOCK_COMMENT_MODE,
                                    STRINGS,
                                    NUMBERS,
                                    CPP_PRIMITIVE_TYPES
                                )
                            )
                        )
                    ),
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE,
                    PREPROCESSOR
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("class struct"),
                end =
                    """[{;:]""",
                contains = listOf(
                    Mode(
                        begin =
                            """<""",
                        end =
                            """>""",
                        contains = listOf(hljs.SELF)
                    ),
                    // skip generic stuff
                    hljs.TITLE_MODE
                )
            )
        ),
        exports = Mode(
            preprocessor = PREPROCESSOR,
            strings = STRINGS,
            keywords = keywords(CPP_KEYWORDS)
        )
    )
}
