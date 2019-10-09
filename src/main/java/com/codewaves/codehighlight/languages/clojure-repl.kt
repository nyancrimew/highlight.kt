package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Clojure REPL
Description = Clojure REPL sessions
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Requires = clojure.js
Category = lisp
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/clojure-repl.js MD5 <43f59eae0078e0196bb537862b8d2475>
 */
internal fun `clojure-repl`(): Mode {
    return Mode(
        contains = listOf(
            Mode(
                className = "meta",
                begin =
                    """^([\w.-]+|\s*#_)?=>""",
                starts = Mode(
                    end =
                        """${'$'}""",
                    subLanguage = "clojure"
                )
            )
        )
    )
}
