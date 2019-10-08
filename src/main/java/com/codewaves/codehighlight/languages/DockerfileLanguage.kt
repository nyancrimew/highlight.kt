package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Dockerfile
Requires = bash.js
Author = Alexis Hénaut <alexis@henaut.net>
Description = language definition for Dockerfile files
Category = config
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class DockerfileLanguage : LanguageBuilder {
    override fun build() = Mode(
        aliases = listOf("docker"),
        case_insensitive = true,
        keywords = keywords("from maintainer expose env arg user onbuild stopsignal"),
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            hljs.NUMBER_MODE,
            Mode(
                beginKeywords = keywords("run cmd entrypoint volume add copy workdir label healthcheck shell"),
                starts = Mode(
                    end =
                        """[^\\]${'$'}""",

                    subLanguage = "bash"
                )
            )
        ),
        illegal = "</"
    )
}
