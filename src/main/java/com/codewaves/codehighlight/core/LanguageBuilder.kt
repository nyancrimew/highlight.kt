package com.codewaves.codehighlight.core

import com.codewaves.codehighlight.core.Mode

/**
 * Created by Sergej Kravcenko on 5/16/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */
interface LanguageBuilder {
   fun build(): Mode
}

typealias hljs = Mode
