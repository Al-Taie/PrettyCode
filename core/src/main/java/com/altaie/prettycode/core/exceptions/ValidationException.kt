package com.altaie.prettycode.core.exceptions

import com.altaie.prettycode.core.exceptions.base.BaseException
import com.altaie.prettycode.core.model.ValidationErrorDto
import org.apache.http.HttpStatus

data class ValidationException(
    override val message: String?,
    override val code: Int = HttpStatus.SC_UNPROCESSABLE_ENTITY,
    val errors: List<ValidationErrorDto>? = null
) : BaseException() {
    val errorMessage: String?
        get() = errors?.firstOrNull()?.message ?: message
}
