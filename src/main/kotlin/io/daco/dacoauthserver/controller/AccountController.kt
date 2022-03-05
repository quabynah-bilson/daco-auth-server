package io.daco.dacoauthserver.controller

import io.daco.dacoauthserver.model.Account
import io.daco.dacoauthserver.model.requests.AuthRequest
import io.daco.dacoauthserver.model.responses.BaseResponse
import io.daco.dacoauthserver.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
class AccountController {

    @Autowired
    private lateinit var repository: AccountRepository

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<BaseResponse> {
        val exists = repository.existsByUsername(request.username)
        return if (exists) {
            val account = repository.findAccountByUsername(request.username)
            ResponseEntity.ok(BaseResponse.Success(account))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.Error("Account does not exist"))
        }
    }


    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequest): ResponseEntity<BaseResponse> {
        val exists = repository.existsByUsername(request.username)
        return if (exists) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.Error("Account with this username already exists"))
        } else {
            val account = Account(
                username = request.username,
                password = request.password,
            )
            val updatedAccount = repository.insert(account)
            ResponseEntity.ok(BaseResponse.Success(updatedAccount))
        }
    }

    @GetMapping("/accounts")
    fun accounts(): ResponseEntity<BaseResponse> {
        val accounts = repository.findAll()
        return ResponseEntity.ok(BaseResponse.Success(accounts))
    }

    @DeleteMapping("accounts/delete")
    fun deleteAccount(@RequestBody account: Account): ResponseEntity<BaseResponse> {
        val exists = repository.existsByUsername(account.username)

        return if (exists) {
            repository.deleteAccountByUsername(account.username)
            ResponseEntity.ok(BaseResponse.Success("Account deleted successfully"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.Error("Account does not exist"))
        }
    }

}