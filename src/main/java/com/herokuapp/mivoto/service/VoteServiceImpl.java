package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.model.User;
import com.herokuapp.mivoto.model.Vote;
import com.herokuapp.mivoto.repository.CrudVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService{

    private final CrudVoteRepository repository;

    @Autowired
    public VoteServiceImpl(CrudVoteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Vote vote(Integer restaurantId, Integer userId, LocalTime currentTime, LocalDate currentDate) {
        Vote vote = repository.create(new Vote(currentDate, new Restaurant(restaurantId), new User(userId)), currentTime);
        if (vote == null) throw new IllegalStateException("User can't re-vote after 11:00");
        return vote;
    }

    public Vote get(int id){
        return repository.getById(id);
    }
    @Override
    public int getCountOfVotesByRestaurantId(int id) {
        return repository.getCountOfVotesByRestaurantId(id);
    }
}
