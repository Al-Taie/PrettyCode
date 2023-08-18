package com.altaie.prettycode.core.model


import com.google.gson.annotations.SerializedName

data class ValidationErrorDto(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("ref")
    val reference: String? = null
)
