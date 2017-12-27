package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
