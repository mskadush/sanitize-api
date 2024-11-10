package com.example.sanitize

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
  fromApplication<FlashHomeworkApplication>().with(TestcontainersConfiguration::class).run(*args)
}
