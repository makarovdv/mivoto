package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        assertMatch(restaurant, TERRA_MARE);
    }

    @Test
    public void getAll(){
        assertMatch(service.getAll(), RESTAURANTS);
    }

    @Test
    public void update(){
        service.update(UPDATED_TERRA_MARE);
        assertMatch(service.getAll(), SICILIANA, SALOTTO, UPDATED_TERRA_MARE);
    }

    @Test
    public void create(){
        Restaurant newRestaurant = new Restaurant(null, "Zeughauskeller", "Bahnhofstrasse 28a, Zurich 8001, Switzerland", "+41442201515");
        Restaurant created = service.create(newRestaurant);
        assertMatch(service.getAll(), SICILIANA, SALOTTO, TERRA_MARE, created);
    }

    @Test
    public void delete(){
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), SICILIANA, SALOTTO);
    }
}
