package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = VBScript
Author = Nikita Ledyaev <lenikita@yandex.ru>
Contributors = Michal Gabrukiewicz <mgabru@gmail.com>
Category = scripting
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class VbscriptLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("vbs"),
        case_insensitive = true,
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "call class const dim do loop erase execute executeglobal exit for each next function if then else on error option explicit new private property let get public randomize redim rem select case set stop sub while wend with end to elseif is or xor and not class_initialize class_terminate default preserve in me byval byref step resume goto"
            ),
            Keyword(
                className = "built_in",

                value = "lcase month vartype instrrev ubound setlocale getobject rgb getref string weekdayname rnd dateadd monthname now day minute isarray cbool round formatcurrency conversions csng timevalue second year space abs clng timeserial fixs len asc isempty maths dateserial atn timer isobject filter weekday datevalue ccur isdate instr datediff formatdatetime replace isnull right sgn array snumeric log cdbl hex chr lbound msgbox ucase getlocale cos cdate cbyte rtrim join hour oct typename trim strcomp int createobject loadpicture tan formatnumber mid scriptenginebuildversion scriptengine split scriptengineminorversion cint sin datepart ltrim sqr scriptenginemajorversion time derived eval date formatpercent exp inputbox left ascw chrw regexp server response request cstr err"
            ),
            Keyword(
                className = "literal",

                value = "true false null nothing empty"
            )
        ),
        illegal = "//",

        contains = listOf(
            hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(contains = listOf(Mode(begin = "\"\"")))),
            hljs.COMMENT(
                """'""", """${'$'}""",

                Mode(
                    relevance = 0
                )
            ),
            hljs.C_NUMBER_MODE
        )
    )
}
