package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.to.MenuTo;
import java.time.LocalDate;

public interface MenuService {

    MenuTo create(MenuTo menu);

    void update(MenuTo menu);

    void delete(int id);

    MenuTo get(int id);

    MenuTo get(LocalDate date, Integer restaurantId);
}
