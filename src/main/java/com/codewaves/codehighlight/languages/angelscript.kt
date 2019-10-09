package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = AngelScript
Author = Melissa Geels <melissa@nimble.tools>
Category = scripting
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/angelscript.js MD5 <cc3f9e676ef984fe1822f991e06ded0e>
 */
internal fun angelscript(): Mode {
    var builtInTypeMode = Mode(
        className = "built_in",
        begin = "\\b(void|bool|int|int8|int16|int32|int64|uint|uint8|uint16|uint32|uint64|string|ref|array|double|float|auto|dictionary)"
    )
    var objectHandleMode = Mode(
        className = "symbol",
        begin = "[a-zA-Z0-9_]+@"
    )
    var genericMode = Mode(
        className = "keyword",
        begin = "<",
        end = ">",
        contains = listOf(builtInTypeMode, objectHandleMode)
    )
    builtInTypeMode.contains = listOf(genericMode)
    objectHandleMode.contains = listOf(genericMode)
    return Mode(
        aliases = listOf("asc"),
        keywords = keywords("for in|0 break continue while do|0 return if else case switch namespace is cast or and xor not get|0 in inout|10 out override set|0 private public const default|0 final shared external mixin|10 enum typedef funcdef this super import from interface abstract|0 try catch protected explicit property"),
        // avoid close detection with C# and JS
        illegal = "(^using\\s+[A-Za-z0-9_\\.]+;\$|\\bfunction\s*[^\\())",
        contains = listOf(
            Mode(// "strings"
                className = "string",
                begin = "'",
                end = "'",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE),
                relevance = 0
            ),
            Mode(// "strings"
                className = "string",
                begin = "\"",
                end = "\"",
                illegal = "\\n",
                contains = listOf(hljs.BACKSLASH_ESCAPE),
                relevance = 0
            ),
            // """heredoc strings"""
            Mode(
                className = "string",
                begin = "\"\"\"",
                end = "\"\"\""
            ),
            hljs.C_LINE_COMMENT_MODE, // single-line comments
            hljs.C_BLOCK_COMMENT_MODE, // comment blocks
            Mode(// interface or namespace declaration
                beginKeywords = keywords("interface namespace"),
                end = "{",
                illegal = "[;.\\-]",
                contains = listOf(
                    Mode(// interface or namespace name
                        className = "symbol",
                        begin = "[a-zA-Z0-9_]+"
                    )
                )
            ),
            Mode(// class declaration
                beginKeywords = keywords("class"),
                end = "{",
                illegal = "[;.\\-]",
                contains = listOf(
                    Mode(// class name
                        className = "symbol",
                        begin = "[a-zA-Z0-9_]+",
                        contains = listOf(
                            Mode(
                                begin = "[:,]\\s*",
                                contains = listOf(
                                    Mode(
                                        className = "symbol",
                                        begin = "[a-zA-Z0-9_]+"
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            builtInTypeMode, // built-in types
            objectHandleMode, // object handles
            Mode(// literals
                className = "literal",
                begin = "\\b(null|true|false)"
            ),
            Mode(// numbers
                className = "number",
                begin = "(-?)(\\b0[xX][a-fA-F0-9]+|(\\b\\d+(\\.\\d*)?f?|\\.\\d+f?)([eE][-+]?\\d+f?)?)"
            )
        )
    )
}
