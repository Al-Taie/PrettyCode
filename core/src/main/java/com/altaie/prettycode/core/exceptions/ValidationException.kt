package com.altaie.prettycode.core.exceptions

import com.altaie.prettycode.core.exceptions.base.BaseException

data class ValidationException(
    override val message: String?,
    override val code: Int
) : BaseException()
