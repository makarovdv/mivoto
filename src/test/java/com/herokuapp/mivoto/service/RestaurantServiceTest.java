package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import java.time.LocalDate;

import static com.herokuapp.mivoto.RestaurantTestData.*;
import static com.herokuapp.mivoto.utils.RestaurantsUtil.fromTo;

public class RestaurantServiceTest extends AbstractServiceTest{
    @Autowired
    private RestaurantService service;

    @Test
    public void get(){
        RestaurantTo restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, TERRA_MARE);
    }

    @Test
    public void get1stPage(){
        Page<RestaurantTo> rs = service.getPage(1);
        assertMatch(rs, RESTAURANTS_PAGE1);
    }

    @Test
    public void get2ndPage(){
        Page<RestaurantTo> rs = service.getPage(2);
        assertMatch(rs, RESTAURANTS_PAGE2);
    }

    @Test
    public void update(){
        service.update(UPDATED_TERRA_MARE);
        assertMatch(service.getPage(2), POROSELLO, SALOTTO, UPDATED_TERRA_MARE);
    }

    @Test
    public void create(){
        Restaurant newRestaurant = getCreated();
        Restaurant created = fromTo(service.create(newRestaurant));
        assertMatch(service.getPage(2), POROSELLO, SALOTTO, TERRA_MARE, created);
    }

    @Test
    public void delete(){
        service.delete(RESTAURANT1_ID + 1); //delete SALOTTO
        assertMatch(service.getPage(2), POROSELLO, TERRA_MARE);
    }

    @Test
    public void get2ndPageWithMenu(){
        Page<RestaurantWithMenuTo> rs = service.getPageWithMenu(2, LocalDate.of(2017, 12, 30));
        System.out.println(rs.toString());
        assertMatchWithMenu(rs, RESTAURANTS_WITH_MENU_PAGE2);
    }
}
