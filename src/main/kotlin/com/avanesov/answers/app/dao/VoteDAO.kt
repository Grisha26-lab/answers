package com.avanesov.answers.app.dao

import com.avanesov.answers.app.domain.Vote
import com.avanesov.answers.app.domain.VoteStat
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class VoteDAO (private val jdbcTemplate: JdbcTemplate) {

    fun addVote(vote: Vote) = jdbcTemplate.update("insert into votes (user_id,vote) values (?, ?)", vote.userId,vote.vote)

    fun getStats():List<VoteStat> = jdbcTemplate.query("select vote, count(*) as total from votes group by vote", BeanPropertyRowMapper(VoteStat::class.java))

    @PostConstruct
    fun init() {
        jdbcTemplate.execute("create table if not exists votes (user_id integer primary key, vote varchar)");
    }
//
//
//
//
//    fun getAllQuestion():Collection<Vote> = jdbcTemplate.query("select * from postgres",BeanPropertyRowMapper())
//
//    fun getQuestionById(id:Long): Vote =
//        jdbcTemplate.query("select * from postgres where id=?", arrayOf(id),BeanPropertyRowMapper<Vote>())
//        .stream()
//        .findAny()
//        .orElseThrow{ IllegalArgumentException("Такого вопроса нету")}
}