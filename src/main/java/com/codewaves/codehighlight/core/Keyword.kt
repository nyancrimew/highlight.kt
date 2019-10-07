package com.codewaves.codehighlight.core

/**
 * Created by Sergej Kravcenko on 5/14/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

data class Keyword(
   val value: String,
   val className: String = "keyword",
   val relevance: Int = 0
)

fun keywords(vararg keywords: Any): List<Keyword> = keywords.map {
   if (it is Keyword) it else Keyword(value = it.toString())
 }