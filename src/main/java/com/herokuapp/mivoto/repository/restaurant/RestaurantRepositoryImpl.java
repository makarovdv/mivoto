package com.herokuapp.mivoto.repository.restaurant;

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
    public Restaurant update(Restaurant r) {
        return restaurantRepository.update(r.getName(), r.getAddress(), r.getPhone(), r.getId()) != 0 ? r : null;
    }

    @Override
    public boolean delete(int id) {
        return restaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Restaurant> getPage(int page) {
        return restaurantRepository.findAll(PageRequest.of(page, PAGE_SIZE, SORT));
    }
}
