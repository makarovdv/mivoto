package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

public interface VoteService {
    Vote vote(Integer restaurantId, Integer userId, LocalTime currentTime, LocalDate currentDate);

    Vote get(int id);

    int getCountOfVotesByRestaurantId(int id);
}
