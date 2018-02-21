package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Vote;
import com.herokuapp.mivoto.repository.CrudUserRepository;
import com.herokuapp.mivoto.repository.VoteRepository;
import com.herokuapp.mivoto.repository.restaurant.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository repository;

    @Autowired
    private CrudUserRepository userRepository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    private static final LocalTime REVOTE_TIME_LIMIT = LocalTime.of(11,0);

    @Transactional
    @Override
    public void vote(Integer restaurantId, Integer userId, LocalTime currentTime, LocalDate currentDate) {
        Vote fromBase = repository.getByDateAndUserId(currentDate, userId);
        if (fromBase==null) {
            repository.save(new Vote(currentDate, restaurantRepository.getOne(restaurantId), userRepository.getOne(userId)));
        } else {
            if (currentTime.isBefore(REVOTE_TIME_LIMIT)){
                fromBase.setRestaurant(restaurantRepository.getOne(restaurantId));
                repository.save(fromBase);
            } else {
                throw new IllegalStateException("User can't re-vote after " + REVOTE_TIME_LIMIT);
            }
        }
    }
}
