package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.LanguageBuilder
import com.codewaves.codehighlight.core.Mode

/*
Language = Clojure REPL
Description = Clojure REPL sessions
Author = Ivan Sagalaev <maniac@softwaremaniacs.org>
Requires = clojure.js
Category = lisp
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ClojurereplLanguage : LanguageBuilder {
    override fun build() = Mode(
        contains = listOf(
            Mode(
                className = "meta",
                begin = """^(listOf(\w.-]+|\s*#_)?=>""",
                starts = Mode(
                    end = """${'$'}""",
                    subLanguage = "clojure"
                )
            )
        )
    )
}
