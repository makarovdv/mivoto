package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.to.MenuTo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.herokuapp.mivoto.DishTestData.THE_PORKIE;
import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get(){
        MenuTo menu = service.get(MENU1_ID);
        assertMatch(menu, MENU1);
    }

    @Test
    public void getByDateAndRestaurantId(){
        MenuTo menu = service.get(LocalDate.of(2017, 12, 30), RESTAURANT1_ID);
        assertMatch(menu, MENU1);
    }
    @Test
    public void create(){
        MenuTo menu = service.create(getCreated());
        assertMatch(service.get(menu.getId()), menu);
    }

    @Test
    public void update(){
        MenuTo menu = UPDATED_MENU1;
        service.update(menu);
        assertMatch(service.get(menu.getId()), UPDATED_MENU1);
    }

    @Test
    public void getNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        service.get(1);
    }

    @Test
    public void deleteNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        service.delete(1);
    }

    @Test
    public void updateNotFoundWithId(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not found entity with id: 1");
        service.update(new MenuTo( 1, LocalDate.of(2017,12,25), Collections.singleton(THE_PORKIE), RESTAURANT1_ID + 4));
    }
}
