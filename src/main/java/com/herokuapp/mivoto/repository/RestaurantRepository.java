package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.Page;

public interface RestaurantRepository {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    Page<Restaurant> getPage(int page);
}
