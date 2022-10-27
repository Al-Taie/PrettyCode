package com.altaie.prettycode.core.mapper

import com.altaie.prettycode.core.exceptions.HttpException
import com.altaie.prettycode.core.mapper.base.Mapper

/**
 * Convert retrofit HttpException to Domain exception
 * */
internal class HttpExceptionMapper : Mapper<retrofit2.HttpException, HttpException> {
    override fun map(from: retrofit2.HttpException): HttpException {
        return HttpException(
            message = from.response()?.errorBody()?.string(),
            code = from.code()
        )
    }
}
