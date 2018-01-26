package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.to.MenuTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuService service;

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
}
