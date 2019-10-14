package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.*
import ch.deletescape.highlight.core.hljs

/*
Language = Objective-C
Author = Valerii Hiora <valerii.hiora@gmail.com>
Contributors = Angel G. Olloqui <angelgarcia.mail@gmail.com>, Matt Diephouse <matt@diephouse.com>, Andrew Farmer <ahfarmer@gmail.com>, Minh Nguyễn <mxn@1ec5.org>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/objectivec.js MD5 <ade954d7efb59d8abeb3b9f70269cd30>
 */
internal fun objectivec(): Mode {
    var API_CLASS = Mode(
        className = "built_in",
        begin = "\\b(AV|CA|CF|CG|CI|CL|CM|CN|CT|MK|MP|MTK|MTL|NS|SCN|SK|UI|WK|XC)\\w+"
    )
    var OBJC_KEYWORDS = listOf(
        keyword(
            className = "keyword",
            value =
                "int float while char export sizeof typedef const struct for union unsigned long volatile static bool mutable if do return goto void enum else break extern asm case short default double register explicit signed typename this switch continue wchar_t inline readonly assign readwrite self @synchronized id typeof nonatomic super unichar IBOutlet IBAction strong weak copy in out inout bycopy byref oneway __strong __weak __block __autoreleasing @private @protected @public @try @property @end @throw @catch @finally @autoreleasepool @synthesize @dynamic @selector @optional @required @encode @package @import @defs @compatibility_alias __bridge __bridge_transfer __bridge_retained __bridge_retain __covariant __contravariant __kindof _Nonnull _Nullable _Null_unspecified __FUNCTION__ __PRETTY_FUNCTION__ __attribute__ getter setter retain unsafe_unretained nonnull nullable null_unspecified null_resettable class instancetype NS_DESIGNATED_INITIALIZER NS_UNAVAILABLE NS_REQUIRES_SUPER NS_RETURNS_INNER_POINTER NS_INLINE NS_AVAILABLE NS_DEPRECATED NS_ENUM NS_OPTIONS NS_SWIFT_UNAVAILABLE NS_ASSUME_NONNULL_BEGIN NS_ASSUME_NONNULL_END NS_REFINED_FOR_SWIFT NS_SWIFT_NAME NS_SWIFT_NOTHROW NS_DURING NS_HANDLER NS_ENDHANDLER NS_VALUERETURN NS_VOIDRETURN"
        ),
        keyword(
            className = "literal",
            value =
                "false true FALSE TRUE nil YES NO NULL"
        ),
        keyword(
            className = "built_in",
            value =
                "BOOL dispatch_once_t dispatch_queue_t dispatch_sync dispatch_async dispatch_once"
        )
    ).flatten()
    var LEXEMES =
        """[a-zA-Z@][a-zA-Z0-9_]*"""
    var CLASS_KEYWORDS = "@interface @class @protocol @implementation"
    return Mode(
        aliases = listOf(
            "mm",
            "objc",
            "obj-c"
        ),
        keywords = OBJC_KEYWORDS,
        lexemes = LEXEMES,
        illegal = "</",
        contains = listOf(
            API_CLASS,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.C_NUMBER_MODE,
            hljs.QUOTE_STRING_MODE,
            Mode(
                className = "string",
                variants = listOf(
                    Mode(
                        begin = "@\"",
                        end = "\"",
                        illegal = "\\n",
                        contains = listOf(hljs.BACKSLASH_ESCAPE)
                    ),
                    Mode(
                        begin = "'",
                        end = "[^\\\\]'",
                        illegal = "[^\\\\][^\"]"
                    )
                )
            ),
            Mode(
                className = "meta",
                begin = "#",
                end = "$",
                contains = listOf(
                    Mode(
                        className = "meta-string",
                        variants = listOf(
                            Mode(
                                begin = "\\\"",
                                end = "\\\""
                            ),
                            Mode(
                                begin = "<",
                                end = ">"
                            )
                        )
                    )
                )
            ),
            Mode(
                className = "class",
                begin = "(" +
                    CLASS_KEYWORDS.split(" ").joinToString("|") +
                    ")\\b",
                end = "(\\{|\$)",
                excludeEnd = true,
                keywords = keywords(CLASS_KEYWORDS),
                lexemes = LEXEMES,
                contains = listOf(
                    hljs.UNDERSCORE_TITLE_MODE
                )
            ),
            Mode(
                begin = "\\." + hljs.UNDERSCORE_IDENT_RE,
                relevance = 0
            )
        )
    )
}
