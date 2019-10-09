package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
 Language = jboss-cli
 Author = Raphaël Parrëe <rparree@edc4it.com>
 Description = language definition jboss cli
 Category = config
 */
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/jboss-cli.js MD5 <1c699d3da4afc3af37f087c2c7c26617>
 */
internal fun `jboss-cli`(): Mode {
    var PARAM = Mode(
        begin =
            """[\w-]+ *=""",
        returnBegin = true,
        relevance = 0,
        contains = listOf(
            Mode(
                className = "attr",
                begin =
                    """[\w-]+"""
            )
        )
    )
    var PARAMSBLOCK = Mode(
        className = "params",
        begin =
            """\(""",
        end =
            """\)""",
        contains = listOf(PARAM),
        relevance = 0
    )
    var OPERATION = Mode(
        className = "function",
        begin =
            """:[\w\-.]+""",
        relevance = 0
    )
    var PATH = Mode(
        className = "string",
        begin =
            """\B(([\/.))[\w\-.\/=]+)+"""
    )
    var COMMAND_PARAMS = Mode(
        className = "params",
        begin =
            """--[\w\-=\/]+"""
    )
    return Mode(
        aliases = listOf("wildfly-cli"),
        lexemes = "[a-z\-]+",
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "alias batch cd clear command connect connection-factory connection-info data-source deploy deployment-info deployment-overlay echo echo-dmr help history if jdbc-driver-info jms-queue|20 jms-topic|20 ls patch pwd quit read-attribute read-operation reload rollout-plan run-batch set shutdown try unalias undeploy unset version xa-data-source"
            ),
            // module
            Keyword(
                className = "literal",
                value = "true false"
            )
        ),
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            hljs.QUOTE_STRING_MODE,
            COMMAND_PARAMS,
            OPERATION,
            PATH,
            PARAMSBLOCK
        )
    )
}
