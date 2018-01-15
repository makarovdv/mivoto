package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.repository.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
    CrudRestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return repository.getById(id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Cacheable("restaurants")
    @Override
    public Page<Restaurant> getPageWithMenuByDate(int page, LocalDate date){
        return repository.getPage(page, date);
    }
}
