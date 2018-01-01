package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Page<Restaurant> getPageWithDishesByDate(int page, LocalDate date);
}
