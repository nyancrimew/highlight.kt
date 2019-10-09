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
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/dockerfile.js MD5 <90c9149dbe9a90709a684c494a8f6e40>
 */
internal fun dockerfile(): Mode {
    return Mode(
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
