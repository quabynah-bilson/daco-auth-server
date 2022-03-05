package io.daco.dacoauthserver.model.responses

/**
 * base response model
 */

sealed class BaseResponse {
    data class Error(val message: String) : BaseResponse()

    data class Success<R>(val data: R) : BaseResponse()
}