package com.example.sanitize

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.testcontainers.containers.MSSQLServerContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

  @Bean
  @ServiceConnection
  fun sqlServerContainer(): MSSQLServerContainer<*> {
    return MSSQLServerContainer(DockerImageName.parse("mcr.microsoft.com/mssql/server:latest")).acceptLicense().withPassword("YourStrong!Passw0rd")
  }

  @Bean(name = ["entityManagerFactory"])
  fun sessionFactory(): LocalSessionFactoryBean {
    val sessionFactory = LocalSessionFactoryBean()

    return sessionFactory
  }
}
