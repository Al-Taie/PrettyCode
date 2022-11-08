package com.altaie.prettycode.core.base

import com.altaie.prettycode.core.exceptions.ResponseException
import com.altaie.prettycode.core.exceptions.ValidationException
import com.altaie.prettycode.core.mapper.HttpExceptionMapper
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

interface BaseRemoteDataSource {
    suspend fun <T, R> apiCall(
        suspendFunction: suspend () -> Response<T>,
        mapper: (T) -> R
    ) = try {
        checkIfSuccessful(result = suspendFunction(), mapper = mapper)
    } catch (t: HttpException) {
        throw httpExceptionMapper.map(from = t)
    }

    private fun <T, R> checkIfSuccessful(
        result: Response<T>,
        mapper: (T) -> R
    ): Resource<R> = if (result.isSuccessful)
        result.body()?.run {
            Resource.Success(mapper(this))
        } ?: Resource.Empty
    else if (result.code() == 422)
        runCatching {
            Resource.Fail(result.errorBody()?.string().fromJson<ValidationException>())
        }.onFailure {
            throw ResponseException(
                message = result.errorBody()?.string().toString(),
                code = result.code()
            )
        }.getOrThrow()
    else
        throw ResponseException(
            message = result.errorBody()?.string().toString(),
            code = result.code()
        )

    private companion object {
        val httpExceptionMapper: HttpExceptionMapper = HttpExceptionMapper()
    }
}
