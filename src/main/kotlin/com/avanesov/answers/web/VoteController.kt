package com.avanesov.answers.web

import com.avanesov.answers.app.dao.VoteDAO
import com.avanesov.answers.app.domain.VoteStat
import com.avanesov.answers.app.domain.Vote
import com.avanesov.answers.web.dto.VoteDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/votes")
class VoteController(val voteDAO: VoteDAO) {

    @PostMapping()
    fun addVote(@RequestBody vote: VoteDto) = voteDAO.addVote(vote.let {
        Vote(vote.userId, vote.vote)
    })

    @GetMapping
    fun getStats():List<VoteStat> = voteDAO.getStats()
}