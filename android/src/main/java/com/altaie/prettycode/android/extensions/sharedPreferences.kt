package com.altaie.prettycode.android.extensions

import android.content.SharedPreferences


fun SharedPreferences.putString(key: String, value: String) = this.edit()
    .putString(key, value)
    .apply()

fun SharedPreferences.putInt(key: String, value: Int) = this.edit()
    .putInt(key, value)
    .apply()

fun SharedPreferences.putBoolean(key: String, value: Boolean) = this.edit()
    .putBoolean(key, value)
    .apply()

fun SharedPreferences.delete(key: String) = this.edit()
    .remove(key)
    .apply()
