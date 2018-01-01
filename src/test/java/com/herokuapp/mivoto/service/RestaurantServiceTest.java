package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.*;

import static com.herokuapp.mivoto.RestaurantTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    public void get(){
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatchIgnoreDishes(restaurant, TERRA_MARE);
    }

    @Test
    public void getAll(){
        assertMatchIgnoreDishes(service.getAll(), RESTAURANTS);
    }

    @Test
    public void update(){
        service.update(UPDATED_TERRA_MARE);
        assertMatchIgnoreDishes(service.getPageWithDishesByDate(2, LocalDate.of(2017,12,30)), POROSELLO, SALOTTO, UPDATED_TERRA_MARE);
    }

    @Test
    public void create(){
        Restaurant newRestaurant = new Restaurant(null, "Via Romano", "Lavochkina St., 34, Moscow 125581, Russia", "+74955453480");
        Restaurant created = service.create(newRestaurant);
        List<Restaurant> restaurantsWithCreated = new ArrayList<>(Arrays.asList(RESTAURANTS));
        restaurantsWithCreated.add(created);
        assertMatchIgnoreDishes(service.getAll(), restaurantsWithCreated);
    }

    @Test
    public void delete(){
        service.delete(RESTAURANT1_ID + 1); //delete SALOTTO
        assertMatchIgnoreDishes(service.getAll(), RESTAURANTS_WITHOUT_SALOTTO);
    }

    @Test
    public void get1stPageWithDishes(){
        Page<Restaurant> rs = service.getPageWithDishesByDate(1, LocalDate.of(2017,12,30));
        assertMatch(rs, PAGE1_RESTAURANTS);
    }

    @Test
    public void get2ndPageWithDishes(){
        Page<Restaurant> rs = service.getPageWithDishesByDate(2, LocalDate.of(2017,12,30));
        assertMatch(rs, PAGE2_RESTAURANTS);
    }
}
