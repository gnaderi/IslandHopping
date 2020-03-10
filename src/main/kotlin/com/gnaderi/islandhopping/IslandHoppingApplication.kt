package com.gnaderi.islandhopping

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class IslandHoppingApplication

fun main(args: Array<String>) {
    runApplication<IslandHoppingApplication>(*args)
}
