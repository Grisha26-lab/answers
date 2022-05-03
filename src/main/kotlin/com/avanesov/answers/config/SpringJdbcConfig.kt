package com.avanesov.answers.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
@ComponentScan("com.avanesov")
class SpringJdbcConfig(
    @Value("\${app.database.driver}")
    private val driver: String,
    @Value("\${app.database.url}")
    private val url: String,
    @Value("\${app.database.username}")
    private val username: String,
    @Value("\${app.database.password}")
    private val password: String


) {


    @Bean
    fun mysqlDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(driver)
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        return dataSource
    }

    @Bean
    fun jdbcTemplate(): JdbcTemplate = JdbcTemplate(mysqlDataSource())
}