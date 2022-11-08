package com.altaie.prettycode.core.usecase.base

import com.altaie.prettycode.core.exceptions.GpsProviderIsDisabledException
import com.altaie.prettycode.core.exceptions.HttpException
import com.altaie.prettycode.core.exceptions.ResponseException
import com.altaie.prettycode.core.exceptions.ValidationException
import com.altaie.prettycode.core.exceptions.base.BaseException
import com.altaie.prettycode.core.exceptions.base.Error
import com.altaie.prettycode.core.mapper.toGpsError
import com.altaie.prettycode.core.mapper.toHttpError
import com.altaie.prettycode.core.mapper.toNetworkError
import com.altaie.prettycode.core.mapper.toResponseError
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.withTimeout
import java.io.IOException
import java.util.concurrent.TimeUnit

abstract class BaseUseCase<T> {

    protected suspend fun <returnType> FlowCollector<T>.timeoutEmit(
        timeoutMs: Long,
        onStartedState: T? = null,
        onEmptyState: T? = null,
        onSuccessState: ((returnType) -> T)? = null,
        onErrorState: ((BaseException) -> T)? = null,
        suspendFunction: suspend () -> returnType
    ) = runCatching {
        withTimeout(timeoutMs) {
            onStartedState?.let { state ->
                emit(state)
            }

            return@withTimeout suspendFunction()
        }
    }.onSuccess { result ->
        onSuccessState?.takeIf { result != null }
            ?.let { wrapper ->
                emit(wrapper(result))
            }

        onEmptyState?.takeUnless { result != null }?.let { state ->
            emit(state)
        }
    }.onFailure { t ->
        val errorMessage = when (t) {
            is IOException -> t.toNetworkError()
            is HttpException -> t.toHttpError()
            is ResponseException -> t.toResponseError()
            is TimeoutCancellationException -> Error.TimeoutError()
            is GpsProviderIsDisabledException -> t.toGpsError()
            is ValidationException -> t
            else -> throw t
        }

        onErrorState?.let { wrapper ->
            emit(wrapper(errorMessage))
        }
    }

    protected companion object {
        val defaultTimeoutMs = TimeUnit.SECONDS.toMillis(30)
    }
}
