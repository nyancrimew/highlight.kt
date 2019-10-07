package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = Access log
 Author = Oleg Efimov <efimovov@gmail.com>
 Description = Apache/Nginx Access Logs
 */

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class AccesslogLanguage : LanguageBuilder  {
  override fun build() = Mode(
    contains = listOf(
      // IP
      Mode(
        className = "number",
begin = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{1,5))?\\b"
      ),
      // Other numbers
      Mode(
        className = "number",
begin = "\\b\\d+\\b",
relevance = 0
      ),
      // Requests
      Mode(
        className = "string",
begin = "\"(GET|POST|HEAD|PUT|DELETE|CONNECT|OPTIONS|PATCH|TRACE)",
end = "\"",
keywords = keywords("GET POST HEAD PUT DELETE CONNECT OPTIONS PATCH TRACE"),
illegal = "\\n",
relevance = 10
      ),
      // Dates
      Mode(
        className = "string",
begin = """\[""",
end = """\]""",
illegal = "\\n"
      ),
      // Strings
      Mode(
        className = "string",
begin = "\"",
end = "\"",
illegal = "\\n"
      )
    )
  )
}
