package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Makefile
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Contributors = Joël Porquet <joel@porquet.org>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/makefile.js MD5 <e1595855490a0a005314eb07b9feaf72>
 */
internal fun makefile(): Mode {
    /* Variables = simple (eg $(var)) and special (eg $@) */
    var VARIABLE = Mode(
        className = "variable",
        variants = listOf(
            Mode(
                begin = "\\\$\\(" +
                    hljs.UNDERSCORE_IDENT_RE + "\\)",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            ),
            Mode(
                begin =
                    """\${'$'}[@%<?\^\+\*]"""
            )
        )
    )
    /* Quoted string with variables inside */
    var QUOTE_STRING = Mode(
        className = "string",
        begin =
            """"""",
        end =
            """"""",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            VARIABLE
        )
    )
    /* Function = ${'$'}(func arg,...) */
    var FUNC = Mode(
        className = "variable",
        begin =
            """\${'$'}\([\w-]+\s""",
        end =
            """\)""",
        keywords = keyword(
                className = "built_in",
                value =
                    "subst patsubst strip findstring filter filter-out sort word wordlist firstword lastword dir notdir suffix basename addsuffix addprefix join wildcard realpath abspath error warning shell origin flavor foreach if or and call eval file value"
        ),
        contains = listOf(
            VARIABLE
        )
    )
    /* Variable assignment */
    var VAR_ASSIG = Mode(
        begin = "^" +
            hljs.UNDERSCORE_IDENT_RE + "\\s*[:+?]?=",
        illegal = "\\n",
        returnBegin = true,
        contains = listOf(
            Mode(
                begin = "^" +
                    hljs.UNDERSCORE_IDENT_RE,
                end = "[:+?]?=",
                excludeEnd = true
            )
        )
    )
    /* Meta targets (.PHONY) */
    var META = Mode(
        className = "meta",
        begin =
            """^\.PHONY = """,
        end =
            """${'$'}""",
        keywords = keyword(
                className = "meta-keyword",
                value = ".PHONY"
        ),
        lexemes =
            """[\.\w]+"""
    )
    /* Targets */
    var TARGET = Mode(
        className = "section",
        begin =
            """^[^\s]+:""",
        end =
            """${'$'}""",
        contains = listOf(VARIABLE)
    )
    return Mode(
        aliases = listOf(
            "mk",
            "mak"
        ),
        keywords =
            keywords("define endef undefine ifdef ifndef ifeq ifneq else endif include -include sinclude override export unexport private vpath"),
        lexemes =
            """[\w-]+""",
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            VARIABLE,
            QUOTE_STRING,
            FUNC,
            VAR_ASSIG,
            META,
            TARGET
        )
    )
}
