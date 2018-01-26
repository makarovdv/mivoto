package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository{

    private final CrudRestaurantRepository restaurantRepository;

    private static final Sort SORT = new Sort(Sort.Direction.ASC, "name", "address", "phone");

    private static final Integer PAGE_SIZE = 10;

    @Autowired
    public RestaurantRepositoryImpl(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.getById(id);
    }

    @Override
    public Page<Restaurant> getPage(int page) {
        return restaurantRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, SORT));
    }
}
