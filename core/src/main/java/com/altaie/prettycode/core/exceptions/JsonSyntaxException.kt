package com.altaie.prettycode.core.exceptions

data class JsonSyntaxException(
    override val message: String?,
) : Throwable()
