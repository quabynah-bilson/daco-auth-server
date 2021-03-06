package io.daco.dacoauthserver.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Account(
    @Id
    var id: String? = null,
    val username: String,
    val password: String,
)
