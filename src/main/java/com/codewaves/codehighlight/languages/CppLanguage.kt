package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = C++
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Contributors = Evgeny Stepanischev <imbolk@gmail.com>, Zaven Muradyan <megalivoithos@gmail.com>, Roel Deckers <admin@codingcat.nl>, Sam Wu <samsam2310@gmail.com>, Jordi Petit <jordi.petit@gmail.com>, Pieter Vantorre <pietervantorre@gmail.com>, Google Inc. (David Benjamin) <davidben@google.com>
Category = common, system
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class CppLanguage : LanguageBuilder {
    val CPP_PRIMITIVE_TYPES = Mode(
        className = "keyword",
        begin = "\\b[a-z\\d_]*_t\\b"
    )

    // https://en.cppreference.com/w/cpp/language/escape
    // \\ \x \xFF \u2837 \u00323747 \374
    val CHARACTER_ESCAPES = "\\\\(x[0-9A-Fa-f]{2}|u[0-9A-Fa-f]{4,8}|[0-7]{3}|\\S)"
    val STRINGS = Mode(
        className = "string",
        variants = listOf(
            Mode(
                begin = "(u8?|U|L)?\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                begin = "(u8?|U|L)?\'(|.)",
                end = "\'",
                illegal = "."
            ),
            Mode(
                begin =
                    """(?:u8?|U|L)?R"(listOf(^()\\ ]{0,16))\((?:.|\n)*?\)\1""""
            )
        )
    )

    val NUMBERS = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0b[01\"]+)"),
            Mode(begin = "(-?)\\b(listOf(\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(u|U|l|L|ul|UL|f|F|b|B)"),
            Mode(begin = "(-?)(\\b0[xX][a-fA-F0-9\"]+|(\\b[\\d\"]+(\\.[\\d\"]*)?|\\.[\\d\"]+)(listOf(eE][-+]?[\\d\"]+)?)")
        ),
        relevance = 0
    )

    val PREPROCESSOR = Mode(
        className = "meta",
        begin =
            """#\s*[a-z]+\b""",
        end =
            """${'$'}""",
        keywords = keywordsJson(
            """
            meta-keyword ="if else elif endif define undef warning error line pragma ifdef ifndef include"
            """.trimIndent()
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

    val FUNCTION_TITLE = hljs.IDENT_RE + "\\s*\\("

    val CPP_KEYWORDS = keywordsJson(
        """
        keyword = "int float while private char catch import module export virtual operator sizeof dynamic_cast|10 typedef const_cast|10 const for static_cast|10 union namespace unsigned long volatile static protected bool template mutable if public friend do goto auto void enum else break extern using asm case typeid short reinterpret_cast|10 default double register explicit signed typename try this switch continue inline delete alignof constexpr consteval constinit decltype concept co_await co_return co_yield requires noexcept static_assert thread_local restrict _Bool complex _Complex _Imaginary atomic_bool atomic_char atomic_schar atomic_uchar atomic_short atomic_ushort atomic_int atomic_uint atomic_long atomic_ulong atomic_llong atomic_ullong new throw return and or not",
        built_in = "std string wstring cin cout cerr clog stdin stdout stderr stringstream istringstream ostringstream auto_ptr deque list queue stack vector map set bitset multiset multimap unordered_set unordered_map unordered_multiset unordered_multimap array shared_ptr abort abs acos asin atan2 atan calloc ceil cosh cos exit exp fabs floor fmod fprintf fputs free frexp fscanf isalnum isalpha iscntrl isdigit isgraph islower isprint ispunct isspace isupper isxdigit tolower toupper labs ldexp log10 log malloc realloc memchr memcmp memcpy memset modf pow printf putchar puts scanf sinh sin snprintf sprintf sqrt sscanf strcat strchr strcmp strcpy strcspn strlen strncat strncmp strncpy strpbrk strrchr strspn strstr tanh tan vfprintf vprintf vsprintf endl initializer_list unique_ptr",
        literal = "true false nullptr NULL"
        """.trimIndent()
    )

    val EXPRESSION_CONTAINS = listOf(
        CPP_PRIMITIVE_TYPES,
        hljs.C_LINE_COMMENT_MODE,
        hljs.C_BLOCK_COMMENT_MODE,
        NUMBERS,
        STRINGS
    )

    override fun build() = Mode(
        aliases = listOf("c", "cc", "h", "c++", "h++", "hpp", "hh", "hxx", "cxx"),
        keywords = CPP_KEYWORDS,
        illegal = "</",
        contains = EXPRESSION_CONTAINS + listOf(
            PREPROCESSOR,
            Mode(
                begin = "\\b(deque|list|queue|stack|vector|map|set|bitset|multiset|multimap|unordered_map|unordered_set|unordered_multiset|unordered_multimap|array)\\s*<",
                end = ">",
                keywords = CPP_KEYWORDS,
                contains = listOf(CPP_PRIMITIVE_TYPES)
            ),
            Mode(
                begin = hljs.IDENT_RE + "::",
                keywords = CPP_KEYWORDS
            ),
            Mode(
                // This mode covers expression context where we can't expect a function
                // definition and shouldn't highlight anything that looks like one =         // `return some()`, `else if()`, `(x*sum(1, 2))`
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
                keywords = CPP_KEYWORDS,
                contains = EXPRESSION_CONTAINS + listOf(
                    Mode(
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        keywords = CPP_KEYWORDS,
                        contains = EXPRESSION_CONTAINS
                    )
                ),
                relevance = 0
            )
        ),
        relevance = 0
    )
}
