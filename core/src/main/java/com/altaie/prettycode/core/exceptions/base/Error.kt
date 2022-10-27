package com.altaie.prettycode.core.exceptions.base

sealed class Error(
    override val message: String?,
    val name: String
    ): BaseException() {
    data class ResponseError(
        override val message: String? = RESPONSE_ERROR,
        override val code: Int
    ) : Error(message = message, name = "ResponseError")

    data class HttpError(
        override val message: String? = HTTP_ERROR,
        override val code: Int
    ) : Error(message = message, name = "HttpError")

    data class UnknownError(
        override val message: String? = UNKNOWN_ERROR,
        override val code: Int = -1
    ) : Error(message = message, name = "UnknownError")

    data class TimeoutError(
        override val message: String? = TIMEOUT_ERROR,
        override val code: Int = 408
    ) : Error(message = message, name = "TimeoutError")

    data class NetworkError(
        override val message: String? = NETWORK_ERROR,
        override val code: Int = 408
    ) : Error(message = message, name = "NetworkError")

    data class GpsError(
        override val message: String? = GPS_ERROR,
        override val code: Int = 200
    ) : Error(message = message, name = "GpsError")

    private companion object Errors {
        const val HTTP_ERROR = "Http Error"
        const val TIMEOUT_ERROR = "Timeout Error"
        const val UNKNOWN_ERROR = "Unknown Error"
        const val NETWORK_ERROR = "No internet access"
        const val RESPONSE_ERROR = "Response Error"
        const val GPS_ERROR = "Gps provider is disabled, try to enable it"
    }
}
