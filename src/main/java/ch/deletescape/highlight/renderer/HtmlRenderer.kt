package ch.deletescape.highlight.renderer;

import ch.deletescape.highlight.core.StyleRenderer;

/*
 * Created by Sergej Kravcenko on 5/16/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

/**
 * Basic HTML renderer similar to Highlight.js
 */
class HtmlRenderer(private val prefix: String) : StyleRenderer {
   private var result: String = ""

   override fun onStart() {
      result = ""
   }
   
   override fun onFinish() {

   }

   override fun onPushStyle(style: String) {
      result += "<span class=\"${prefix}${style}\">"
   }

   override fun onPopStyle() {
      result += "</span>"
   }

   override fun onPushCodeBlock(block: CharSequence) {
      result += block.toString().escape()
   }

   override fun onPushSubLanguage(name: String?, code: CharSequence?) {
      result += "<span class=\"$name\">$code</span>"
   }

   override fun onAbort(code: CharSequence) {
      result = code.toString()
   }

   override fun getResult() = result

   private fun String.escape() = replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
}
