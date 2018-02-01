package com.herokuapp.mivoto.repository.menu;

import com.herokuapp.mivoto.model.Menu;

import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    boolean delete(int id);

    Menu get(int id);

    Page<Menu> getPage(int page, LocalDate date);

    List<Menu> getByRestaurantId(List<Integer> id, LocalDate date);

    Menu get(LocalDate date, Integer restaurantId);
}
