package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Tcl
Author = Radek Liska <radekliska@gmail.com>
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/tcl.js MD5 <91a36def187a544535103d2f5f0db6f1>
 */
internal fun tcl(): Mode {
    return Mode(
        aliases = listOf("tk"),
        keywords = keywords("after append apply array auto_execok auto_import auto_load auto_mkindex auto_mkindex_old auto_qualify auto_reset bgerror binary break catch cd chan clock close concat continue dde dict encoding eof error eval exec exit expr fblocked fconfigure fcopy file fileevent filename flush for foreach format gets glob global history http if incr info interp join lappend|10 lassign|10 lindex|10 linsert|10 list llength|10 load lrange|10 lrepeat|10 lreplace|10 lreverse|10 lsearch|10 lset|10 lsort|10 mathfunc mathop memory msgcat namespace open package parray pid pkg::create pkg_mkIndex platform platform::shell proc puts pwd read refchan regexp registry regsub|10 rename return safe scan seek set socket source split string subst switch tcl_endOfWord tcl_findLibrary tcl_startOfNextWord tcl_startOfPreviousWord tcl_wordBreakAfter tcl_wordBreakBefore tcltest tclvars tell time tm trace unknown unload unset update uplevel upvar variable vwait while"),
        contains = listOf(
            hljs.COMMENT(
                ";[ \\t]*#",
                "\$"
            ),
            hljs.COMMENT(
                "^[ \\t]*#",
                "\$"
            ),
            Mode(
                beginKeywords = keywords("proc"),
                end = "[\\{]",
                excludeEnd = true,
                contains = listOf(
                    Mode(
                        className = "title",
                        begin = "[ \\t\\n\\r]+(::)?[a-zA-Z_]((::)?[a-zA-Z0-9_])*",
                        end = "[ \\t\\n\\r]",
                        endsWithParent = true,
                        excludeEnd = true
                    )
                )
            ),
            Mode(
                excludeEnd = true,
                variants = listOf(
                    Mode(
                        begin = "\\\$(\\{)?(::)?[a-zA-Z_]((::)?[a-zA-Z0-9_])*\\(([a-zA-Z0-9_])*\\)",
                        end = "[^a-zA-Z0-9_\\}\\\$]"
                    ),
                    Mode(
                        begin = "\\\$(\\{)?(::)?[a-zA-Z_]((::)?[a-zA-Z0-9_])*",
                        end = "(\\))?[^a-zA-Z0-9_\\}\\$]"
                    )
                )
            ),
            Mode(
                className = "string",
                contains = listOf(hljs.BACKSLASH_ESCAPE),
                variants = listOf(
                    hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null))
                )
            ),
            Mode(
                className = "number",
                variants = listOf(hljs.BINARY_NUMBER_MODE, hljs.C_NUMBER_MODE)
            )
        )
    )
}
