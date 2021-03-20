package com.tamastudy.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class RestapiApplication

fun main(args: Array<String>) {
    runApplication<RestapiApplication>(*args)
}
