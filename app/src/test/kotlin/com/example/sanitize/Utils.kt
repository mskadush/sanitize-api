package com.example.sanitize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals


infix fun <T,U> T?.shouldBe(other: U) {
  assertEquals(other, this)
}
infix fun <T,U> T?.shouldNotBe(other: U) {
  assertNotEquals(other, this)
}