package com.altaie.prettycode.core.utils.extensions

import com.altaie.prettycode.core.base.Resource
import com.altaie.prettycode.core.exceptions.EmptyBodyException
import com.altaie.prettycode.core.exceptions.JsonSyntaxException
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


val gson: Gson by lazy { Gson() }

inline fun <reified T> T?.toJson(): String = runCatching {
    gson.toJson(this)
}.onFailure {
    throw JsonSyntaxException(
        message = "Filed to convert ${T::class.simpleName} to JsonString",
    )
}.getOrThrow()

inline fun <reified T> String?.fromJson(): T = runCatching {
    gson.fromJson(this, T::class.java)
}.onFailure {
    throw JsonSyntaxException(
        message = "Filed to convert JsonString to ${T::class.simpleName}",
    )
}.getOrThrow()

fun <T> Resource<T>?.getOrThrowEmpty() = this?.toData ?: throw EmptyBodyException()

fun String?.toJsonObject(): JsonObject? = runCatching {
    Json.parseToJsonElement(this!!).jsonObject
}.getOrNull()

fun String?.getValueOf(key: String) = runCatching {
    toJsonObject()?.get(key)?.jsonPrimitive?.content
}.getOrNull()
