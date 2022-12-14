package com.altaie.prettycode.core.usecase.params

import com.altaie.prettycode.core.base.Resource
import com.altaie.prettycode.core.mapper.toResource
import com.altaie.prettycode.core.mapper.toUnKnownError
import com.altaie.prettycode.core.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class ResultUseCase<in I, O> : BaseUseCase<Resource<O>>() {
    operator fun invoke(
        params: I,
        timeoutMs: Long = defaultTimeoutMs,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): Flow<Resource<O>> = flow {
        timeoutEmit(
            timeoutMs = timeoutMs,
            onStartedState = Resource.Loading,
            onEmptyState = Resource.Empty,
            onSuccessState = { Resource.Success(data = it) },
            onErrorState = { it.toResource() },
            suspendFunction = { doWork(params) }
        )
    }.flowOn(dispatcher)
        .catch { t -> emit(t.toUnKnownError().toResource()) }

    suspend fun executeSync(params: I): O = doWork(params)

    protected abstract suspend fun doWork(params: I): O
}
