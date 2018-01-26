package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;

import org.springframework.data.domain.Page;
import java.time.LocalDate;

public interface RestaurantService {

    RestaurantTo create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    RestaurantTo get(int id);

    Page<RestaurantTo> getPage(int page);

    // can return value with empty MenuTo
    Page<RestaurantWithMenuTo> getPageWithMenu(int page, LocalDate date);

    // returns value only with MenuTo
    Page<RestaurantWithMenuTo> getPageOnlyWithMenu(int page, LocalDate date);
}
