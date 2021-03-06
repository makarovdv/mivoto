package com.herokuapp.mivoto.repository.restaurant;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.Page;

public interface RestaurantRepository {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Page<Restaurant> getPage(int page);
}
