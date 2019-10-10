package ch.deletescape.highlight.core

import ch.deletescape.highlight.renderer.HtmlRenderer

/**
 * Created by Sergej Kravcenko on 5/17/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

class TestRendererFactory : StyleRendererFactory {
    override fun create(languageName: String): StyleRenderer {
        return HtmlRenderer("hljs-")
    }
}