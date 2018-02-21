package com.herokuapp.mivoto.service;

import java.time.LocalDate;
import java.time.LocalTime;

public interface VoteService {
    void vote(Integer restaurantId, Integer userId, LocalTime currentTime, LocalDate currentDate);
}
