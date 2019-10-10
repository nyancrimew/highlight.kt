package ch.deletescape.highlight.core;

import ch.deletescape.highlight.renderer.HtmlRenderer;

/**
 * Created by Sergej Kravcenko on 5/17/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

public class TestRendererFactory implements StyleRendererFactory {
   public StyleRenderer create(String languageName) {
      return new HtmlRenderer("hljs-");
   }
}
