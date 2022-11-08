package com.altaie.prettycode.core.utils.extenstions

import com.altaie.prettycode.core.exceptions.JsonSyntaxException
import com.google.gson.Gson

val gson: Gson by lazy { Gson() }

inline fun <reified T> T?.toJson(): String = runCatching{
    gson.toJson(this)
}.onFailure {
    throw JsonSyntaxException(
        message = "Filed to convert ${T::class.simpleName} to JsonString",
    )
}.getOrThrow()

inline fun<reified T> String?.fromJson(): T = runCatching{
    gson.fromJson(this, T::class.java)
}.onFailure {
    throw JsonSyntaxException(
        message = "Filed to convert JsonString to ${T::class.simpleName}",
    )
}.getOrThrow()
