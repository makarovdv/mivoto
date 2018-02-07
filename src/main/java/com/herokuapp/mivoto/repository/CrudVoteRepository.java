package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer>{
    Vote getByDateAndUserId(LocalDate date, Integer user_id);

    default Vote create(Vote vote, LocalTime currentTime){
        Vote fromBase = getByDateAndUserId(vote.getDate(), vote.getUser().getId());
        if (fromBase==null) {
            return save(vote);
        } else {
            vote.setId(fromBase.getId());
            if (currentTime.isBefore(LocalTime.of(11,0))){
                return save(vote);
            }
            return null;
        }
    }

    @Query("SELECT COUNT(v.id) FROM Vote v WHERE v.restaurant.id = ?1")
    Integer getCountOfVotesByRestaurantId(Integer id);
}
