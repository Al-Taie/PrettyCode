package com.altaie.prettycode.core.mapper

import com.altaie.prettycode.core.exceptions.base.Error
import com.altaie.prettycode.core.base.Resource
import com.altaie.prettycode.core.exceptions.GpsProviderIsDisabledException
import com.altaie.prettycode.core.exceptions.HttpException
import com.altaie.prettycode.core.exceptions.ResponseException
import java.io.IOException

fun Error.toResource() = Resource.Fail(error = this)

fun Throwable.toUnKnownError() = Error.UnknownError(message = message)

fun IOException.toNetworkError() = Error.NetworkError(message = message)

fun HttpException.toHttpError() = Error.HttpError(message = message, code = code)

fun ResponseException.toResponseError() = Error.ResponseError(message = message, code = code)

fun GpsProviderIsDisabledException.toGpsError() = Error.GpsError(message = message, code = code)
