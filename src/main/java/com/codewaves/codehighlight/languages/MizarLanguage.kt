package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Mizar
Author = Kelley van Evert <kelleyvanevert@gmail.com>
Category = scientific
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class MizarLanguage : LanguageBuilder {
    override fun build() = Mode(
        keywords = keywords("environ vocabularies notations constructors definitions registrations theorems schemes requirements begin end definition registration cluster existence pred func defpred deffunc theorem proof let take assume then thus hence ex for st holds consider reconsider such that and in provided of as from be being by means equals implies iff redefine define now not or attr is mode suppose per cases set thesis contradiction scheme reserve struct correctness compatibility coherence symmetry assymetry reflexivity irreflexivity connectedness uniqueness commutativity idempotence involutiveness projectivity"),
        contains = listOf(
            hljs.COMMENT(
                "::",
                "\$"
            )
        )
    )
}
