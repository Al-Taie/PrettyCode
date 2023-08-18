package com.altaie.prettycode.core.utils.extenstions


fun <T, R> T?.onNull(block: () -> R): R? =
    if (this != null) block() else null

fun <T> List<T>.update(index: Int, value: T): List<T> =
    toMutableList().apply { set(index, value) }

fun <T> List<T>.update(index: Int, value: T.() -> T): List<T> =
    toMutableList().apply { set(index, value(get(index))) }
