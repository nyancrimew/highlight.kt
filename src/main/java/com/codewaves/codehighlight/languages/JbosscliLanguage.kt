package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = jboss-cli
 Author = Raphaël Parrëe <rparree@edc4it.com>
 Description = language definition jboss cli
 Category = config
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class JbosscliLanguage : LanguageBuilder {
    val PARAM = Mode(
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
    val PARAMSBLOCK = Mode(
        className = "params",

        begin =
            """\(""",

        end =
            """\)""",

        contains = listOf(PARAM),
        relevance = 0
    )
    val OPERATION = Mode(
        className = "function",

        begin =
            """:[\w\-.]+""",

        relevance = 0
    )
    val PATH = Mode(
        className = "string",

        begin =
            """\B((listOf(\/.))[\w\-.\/=]+)+"""

    )
    val COMMAND_PARAMS = Mode(
        className = "params",

        begin =
            """--[\w\-=\/]+"""

    )
    override fun build() = Mode(
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
