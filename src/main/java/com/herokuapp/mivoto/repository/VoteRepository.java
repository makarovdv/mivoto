package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface VoteRepository extends JpaRepository<Vote, Integer>{
    Vote getByDateAndUserId(LocalDate date, Integer user_id);
}
