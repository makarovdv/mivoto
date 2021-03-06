package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import static com.herokuapp.mivoto.RestaurantTestData.*;
import static com.herokuapp.mivoto.utils.RestaurantsUtil.fromTo;

public class RestaurantServiceTest extends AbstractServiceTest{
    @Autowired
    private RestaurantService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get() {
        RestaurantTo restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, TERRA_MARE);
    }

    @Test
    public void getNotFoundWithId() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        service.get(1);
    }

    @Test
    public void deleteNotFoundWithId() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        service.delete(1);
    }

    @Test
    public void updateNotFoundWithId() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        Restaurant notExist = getCreated();
        notExist.setId(1);
        service.update(notExist);
    }

    @Test
    public void get1stPage(){
        PageTo<RestaurantTo> rs = service.getPage(0);
        assertMatch(rs.getContent(), RESTAURANTS_PAGE1);
    }

    @Test
    public void get2ndPage(){
        PageTo<RestaurantTo> rs = service.getPage(1);
        assertMatch(rs.getContent(), RESTAURANTS_PAGE2);
    }

    @Test
    public void update(){
        service.update(UPDATED_TERRA_MARE);
        assertMatch(service.getPage(1).getContent(), POROSELLO, SALOTTO, UPDATED_TERRA_MARE);
    }

    @Test
    public void create(){
        Restaurant newRestaurant = getCreated();
        Restaurant created = fromTo(service.create(newRestaurant));
        assertMatch(service.getPage(1).getContent(), POROSELLO, SALOTTO, TERRA_MARE, created);
    }

    @Test
    public void delete(){
        service.delete(RESTAURANT1_ID + 1); //delete SALOTTO
        assertMatch(service.getPage(1).getContent(), POROSELLO, TERRA_MARE);
    }

    @Test
    public void get2ndPageWithMenu(){
        PageTo<RestaurantWithMenuTo> rs = service.getPageWithMenu(1, LocalDate.of(2017, 12, 30));
        assertMatchWithMenu(rs.getContent(), RESTAURANTS_WITH_MENU_PAGE2);
    }
}
