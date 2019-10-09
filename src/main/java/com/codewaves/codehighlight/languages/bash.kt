package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Bash
Author = vah <vahtenberg@gmail.com>
Contributrors = Benjamin Pannell <contact@sierrasoftworks.com>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/bash.js MD5 <4006145b1d42f143c4773bbe6fdb7cc1>
 */
internal fun bash(): Mode {
    var VAR = Mode(
        className = "variable",
        variants = listOf(
            Mode(begin =
                    """\${'$'}[\w\d#@][\w\d_]*"""),
            Mode(begin =
                    """\${'$'}\{(.*?)}""")
        )
    )
    var QUOTE_STRING = Mode(
        className = "string",
        begin =
            """"""",
        end =
            """"""",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            VAR,
            Mode(
                className = "variable",
                begin =
                    """\${'$'}\(""",
                end =
                    """\)""",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            )
        )
    )
    var ESCAPED_QUOTE = Mode(
        className = "",
        begin =
            """\\""""
    )
    var APOS_STRING = Mode(
        className = "string",
        begin =
            """'""",
        end =
            """'"""
    )
    return Mode(
        aliases = listOf("sh\", \"zsh"),
        lexemes =
            """\b-?[a-z\._]+\b""",
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "if then else elif fi for while in do done case esac function"
            ),
            Keyword(
                className = "literal",
                value = "true false"
            ),
            Keyword(
                className = "built_in",
                value = // Shell built-ins
                    // http://www.gnu.org/software/bash/manual/html_node/Shell-Builtin-Commands.html
                    "break cd continue eval exec exit export getopts hash pwd readonly return shift test times trap umask unset alias bind builtin caller command declare echo enable help let local logout mapfile printf read readarray source type typeset ulimit unalias set shopt autoload bg bindkey bye cap chdir clone comparguments compcall compctl compdescribe compfiles compgroups compquote comptags comptry compvalues dirs disable disown echotc echoti emulate fc fg float functions getcap getln history integer jobs kill limit log noglob popd print pushd pushln rehash sched setcap setopt stat suspend ttyctl unfunction unhash unlimit unsetopt vared wait whence where which zcompile zformat zftp zle zmodload zparseopts zprof zpty zregexparse zsocket zstyle ztcp"
            ),
            Keyword(
                className = "_",
                value = "-ne -eq -lt -gt -f -d -e -s -l -a" // relevance booster
            )
        ),
        contains = listOf(
            Mode(
                className = "meta",
                begin =
                    """^#![^\n]+sh\s*${'$'}""",
                relevance = 10
            ),
            Mode(
                className = "function",
                begin =
                    """\w[\w\d_]*\s*\(\s*\)\s*\{""",
                returnBegin = true,
                contains = listOf(hljs.inherit(hljs.TITLE_MODE, Mode(begin =
                        """\w[\w\d_]*"""))),
                relevance = 0
            ),
            hljs.HASH_COMMENT_MODE,
            QUOTE_STRING,
            ESCAPED_QUOTE,
            APOS_STRING,
            VAR
        )
    )
}
