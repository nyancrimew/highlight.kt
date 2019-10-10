package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Rust
Author = Andrey Vlasovskikh <andrey.vlasovskikh@gmail.com>
Contributors = Roman Shmatov <romanshmatov@gmail.com>, Kasper Andersen <kma_untrusted@protonmail.com>
Category = system
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/rust.js MD5 <944a870df40c227ab39873c82bc14bbb>
 */
internal fun rust(): Mode {
    var NUM_SUFFIX = "([ui](8|16|32|64|128|size)|f(32|64))\?"
    var KEYWORDS =
        "abstract as async await become box break const continue crate do dyn else enum extern false final fn for if impl in let loop macro match mod move mut override priv pub ref return self Self static struct super trait true try type typeof unsafe unsized use virtual where while yield"
    var BUILTINS =
        // functions
        "drop i8 i16 i32 i64 i128 isize u8 u16 u32 u64 u128 usize f32 f64 str char bool Box Option Result String Vec Copy Send Sized Sync Drop Fn FnMut FnOnce ToOwned Clone Debug PartialEq PartialOrd Eq Ord AsRef AsMut Into From Default Iterator Extend IntoIterator DoubleEndedIterator ExactSizeIterator SliceConcatExt ToString assert! assert_eq! bitflags! bytes! cfg! col! concat! concat_idents! debug_assert! debug_assert_eq! env! panic! file! format! format_args! include_bin! include_str! line! local_data_key! module_path! option_env! print! println! select! stringify! try! unimplemented! unreachable! vec! write! writeln! macro_rules! assert_ne! debug_assert_ne!"
    return Mode(
        aliases = listOf("rs"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value =
                    KEYWORDS
            ),
            Keyword(
                className = "literal",
                value =
                    "true false Some None Ok Err"
            ),
            Keyword(
                className = "built_in",
                value =
                    BUILTINS
            )
        ),
        lexemes = hljs.IDENT_RE + "!?",
        illegal = "</",
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.COMMENT(
                "/\\*",
                "\\*/",
                Mode(contains = listOf(hljs.SELF))
            ),
            hljs.inherit(
                hljs.QUOTE_STRING_MODE,
                Mode(
                    begin =
                        """b?"""",
                    illegal = null
                )
            ),
            Mode(
                className = "string",
                variants = listOf(
                    Mode(begin =
                            """r(#*)"(.|\n)*?\"\1(?!#)\"""),
                    Mode(begin =
                            """b?'\\?(x\w{2}|u\w{4}|U\w{8}|.)'""")
                )
            ),
            Mode(
                className = "symbol",
                begin =
                    """'[a-zA-Z_][a-zA-Z0-9_]*"""
            ),
            Mode(
                className = "number",
                variants = listOf(
                    Mode(
                        begin = "\\b0b([01_]+)" +
                            NUM_SUFFIX
                    ),
                    Mode(
                        begin = "\\b0o([0-7_]+)" +
                            NUM_SUFFIX
                    ),
                    Mode(
                        begin = "\\b0x([A-Fa-f0-9_]+)" +
                            NUM_SUFFIX
                    ),
                    Mode(
                        begin = "\\b(\\d[\\d_]*(\\.[0-9_]+)?([eE][+-]?[0-9_]+)?)" +
                            NUM_SUFFIX
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("fn"),
                end = "(\\(|<)",
                excludeEnd = true,
                contains = listOf(hljs.UNDERSCORE_TITLE_MODE)
            ),
            Mode(
                className = "meta",
                begin = "#\\!?\\[",
                end = "\\]",
                contains = listOf(
                    Mode(
                        className = "meta-string",
                        begin =
                            """"""",
                        end =
                            """""""
                    )
                )
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("type"),
                end = ";",
                contains = listOf(
                    hljs.inherit(hljs.UNDERSCORE_TITLE_MODE, Mode(endsParent = true))
                ),
                illegal = "\\S"
            ),
            Mode(
                className = "class",
                beginKeywords = keywords("trait enum struct union"),
                end = "{",
                contains = listOf(
                    hljs.inherit(hljs.UNDERSCORE_TITLE_MODE, Mode(endsParent = true))
                ),
                illegal = "[\\w\\d]"
            ),
            Mode(
                begin = hljs.IDENT_RE + "::",
                keywords = listOf(
                    Keyword(
                        className = "built_in",
                        value = BUILTINS
                    )
                )
            ),
            Mode(
                begin = "->"
            )
        )
    )
}
