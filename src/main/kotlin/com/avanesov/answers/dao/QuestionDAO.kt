package com.avanesov.answers.dao

import com.avanesov.answers.model.Question
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class QuestionDAO (private val jdbcTemplate: JdbcTemplate) {

    fun getAllQuestion():Collection<Question> = jdbcTemplate.query("select * from postgres",BeanPropertyRowMapper())

    fun getQuestionById(id:Long):Question =
        jdbcTemplate.query("select * from postgres where id=?", arrayOf(id),BeanPropertyRowMapper<Question>())
        .stream()
        .findAny()
        .orElseThrow{ IllegalArgumentException("Такого вопроса нету")}
}