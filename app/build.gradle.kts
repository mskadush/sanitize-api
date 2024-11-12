plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.jpa)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

dependencies {
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.starter.data.jpa)
  implementation(libs.spring.boot.starter.jdbc)
  implementation(libs.spring.boot.starter.security)
  implementation(libs.spring.swagger)
  implementation(libs.spring.boot.starter.web)
  implementation(libs.jackson.module.kotlin)
  implementation(libs.flyway.core)
  implementation(libs.flyway.mysql)
  implementation(libs.kotlin.reflect)
  runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
//  runtimeOnly("io.micrometer:micrometer-registry-otlp")
  testImplementation(libs.testing.spring.boot.starter.test)
  testImplementation(libs.testing.spring.boot.testcontainers)
  testImplementation(libs.testing.kotlin.test.junit5)
  testImplementation(libs.testing.spring.restdocs.mockmvc)
  testImplementation(libs.testing.spring.security.test)
  testImplementation(libs.testing.junit.jupiter)
  testImplementation(libs.testing.mssqlserver)
  testRuntimeOnly(libs.testing.junit.platform.launcher)
  testImplementation(libs.testing.mockk)

}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
