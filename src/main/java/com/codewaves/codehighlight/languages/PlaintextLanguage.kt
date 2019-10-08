package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = plaintext
Author = Egor Rogov (e.rogov@postgrespro.ru)
Description =     Plain text without any highlighting.
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class PlaintextLanguage : LanguageBuilder {
    override fun build() = Mode(
        disableAutodetect = true
    )
}
