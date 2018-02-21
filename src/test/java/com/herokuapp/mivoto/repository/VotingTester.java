package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VotingTester extends JpaRepository<Vote, Integer> {
    @Query("SELECT COUNT(v.id) FROM Vote v WHERE v.restaurant.id = ?1")
    int getCountOfVotesByRestaurantId(Integer id);
}
