package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Django
Requires = xml.js
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Contributors = Ilya Baryshev <baryshev@gmail.com>
Category = template
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/django.js MD5 <d264fe1828adb1269e914b23a327e959>
 */
internal fun django(): Mode {
    var FILTER = Mode(
        begin =
            """\|[A-Za-z]+:?""",
        keywords = listOf(
            Keyword(
                className = "name",
                value =
                    "truncatewords removetags linebreaksbr yesno get_digit timesince random striptags filesizeformat escape linebreaks length_is ljust rjust cut urlize fix_ampersands title floatformat capfirst pprint divisibleby add make_list unordered_list urlencode timeuntil urlizetrunc wordcount stringformat linenumbers slice date dictsort dictsortreversed default_if_none pluralize lower join center default truncatewords_html upper length phone2numeric wordwrap time addslashes slugify first escapejs force_escape iriencode last safe safeseq truncatechars localize unlocalize localtime utc timezone"
            )
        ),
        contains = listOf(
            hljs.QUOTE_STRING_MODE,
            hljs.APOS_STRING_MODE
        )
    )
    return Mode(
        aliases = listOf("jinja"),
        case_insensitive = true,
        subLanguage = "xml",
        contains = listOf(
            hljs.COMMENT("""\{%\s*comment\s*%}""", """\{%\s*endcomment\s*%}"""),
            hljs.COMMENT("""\{#""", """#}"""),
            Mode(
                className = "template-tag",
                begin =
                    """\{%""",
                end =
                    """%}""",
                contains = listOf(
                    Mode(
                        className = "name",
                        begin =
                            """\w+""",
                        keywords = listOf(
                            Keyword(
                                className = "name",
                                value =
                                    "comment endcomment load templatetag ifchanged endifchanged if endif firstof for endfor ifnotequal endifnotequal widthratio extends include spaceless endspaceless regroup ifequal endifequal ssi now with cycle url filter endfilter debug block endblock else autoescape endautoescape csrf_token empty elif endwith static trans blocktrans endblocktrans get_static_prefix get_media_prefix plural get_current_language language get_available_languages get_current_language_bidi get_language_info get_language_info_list localize endlocalize localtime endlocaltime timezone endtimezone get_current_timezone verbatim"
                            )
                        ),
                        starts = Mode(
                            endsWithParent = true,
                            keywords = keywords("in by as"),
                            contains = listOf(FILTER),
                            relevance = 0
                        )
                    )
                )
            ),
            Mode(
                className = "template-variable",
                begin =
                    """\{\{""",
                end =
                    """)}""",
                contains = listOf(FILTER)
            )
        )
    )
}
