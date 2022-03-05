package io.daco.dacoauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


/**
 * install and use mongodb for mac
 *
 * 1. brew tap mongodb/brew
 * 2. brew install mongodb-community
 * 3. brew services start mongodb-community
 * 4. brew services stop mongodb
 */

@SpringBootApplication
class DacoAuthServerApplication

fun main(args: Array<String>) {
    runApplication<DacoAuthServerApplication>(*args)
}
