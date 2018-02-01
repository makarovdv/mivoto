package com.herokuapp.mivoto.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;
import static com.herokuapp.mivoto.UserTestData.ADMIN_ID;
import static org.junit.Assert.assertEquals;

public class VoteServiceTest extends AbstractServiceTest{
    @Autowired
    private VoteService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final int COUNT = 3;

    @Test
    public void create(){
        voteBeforeEleven();
        assertEquals((long)service.getCountOfVotesByRestaurantId(RESTAURANT1_ID + 1), COUNT + 1);
    }

    @Test()
    public void createAfterEleven(){
        voteAfterEleven();
    }

    @Test
    public void voteBeforeElevenTwice(){
        voteBeforeEleven();
        voteBeforeEleven();
        assertEquals(service.getCountOfVotesByRestaurantId(RESTAURANT1_ID + 1), COUNT + 1);
    }

    @Test
    public void voteAfterElevenTwiceIllegal(){
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("User can't re-vote after 11:00");
        voteAfterEleven();
        voteAfterEleven();
    }

    @Test
    public void getCountOfVotesByRestaurantId(){
        assertEquals(service.getCountOfVotesByRestaurantId(RESTAURANT1_ID + 1), COUNT);
    }

    public void voteBeforeEleven(){
        service.vote(RESTAURANT1_ID + 1, ADMIN_ID, LocalTime.of(10,0), LocalDate.of(2017,12,30));
    }

    public void voteAfterEleven(){
        service.vote(RESTAURANT1_ID + 1, ADMIN_ID, LocalTime.of(12,0), LocalDate.of(2017,12,30));
    }
}
