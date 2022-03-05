package io.daco.dacoauthserver.repository

import io.daco.dacoauthserver.model.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : MongoRepository<Account, String> {

    fun existsByUsername(username: String): Boolean

    fun findAccountByUsername(username: String): Account

    fun deleteAccountByUsername(username: String)
}