package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.PageTo;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;

import java.time.LocalDate;

public interface RestaurantService {

    RestaurantTo create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    RestaurantTo get(int id);

    PageTo<RestaurantTo> getPage(int page);

    // can return value with empty MenuTo
    PageTo<RestaurantWithMenuTo> getPageWithMenu(int page, LocalDate date);

    // returns value with no empty MenuTo
    PageTo<RestaurantWithMenuTo> getPageOnlyWithMenu(int page, LocalDate date);
}
