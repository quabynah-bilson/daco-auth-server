package io.daco.dacoauthserver.model.requests

/**
 * sent from client as request body
 *
 * {
 *      "username" : "dennis@mail.com",
 *      "password": "password"
 *  }
 */
data class AuthRequest(val username: String, val password: String)