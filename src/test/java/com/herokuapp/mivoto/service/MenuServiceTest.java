package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.to.MenuTo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import java.time.LocalDate;
import java.util.Collections;

import static com.herokuapp.mivoto.DishTestData.THE_PORKIE;
import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheManager cacheManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get(){
        MenuTo menu = menuService.get(MENU1_ID);
        assertMatch(menu, MENU1);
    }

    @Test
    public void getByDateAndRestaurantId(){
        MenuTo menu = menuService.get(LocalDate.of(2017, 12, 30), RESTAURANT1_ID);
        assertMatch(menu, MENU1);
    }
    @Test
    public void create(){
        MenuTo menu = menuService.create(getCreated());
        assertMatch(menuService.get(menu.getId()), menu);
    }

    @Test
    public void update(){
        menuService.update(UPDATED_MENU1);
        assertMatch(menuService.get(UPDATED_MENU1.getId()), UPDATED_MENU1);
    }

    @Test
    public void getNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        menuService.get(1);
    }

    @Test
    public void deleteNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        menuService.delete(1);
    }

    @Test
    public void updateNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        menuService.update(new MenuTo( 1, LocalDate.of(2017,12,25), Collections.singleton(THE_PORKIE), RESTAURANT1_ID + 4));
    }

    private Cache getCache(){
        return cacheManager.getCache("restaurants_with_menu");
    }
}
