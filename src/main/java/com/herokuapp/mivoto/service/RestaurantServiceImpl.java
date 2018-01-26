package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.repository.CrudMenuRepository;
import com.herokuapp.mivoto.repository.RestaurantRepository;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.herokuapp.mivoto.utils.RestaurantsUtil.asTo;
import static com.herokuapp.mivoto.utils.RestaurantsUtil.asToWithMenu;
import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final CrudMenuRepository menuRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, CrudMenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    @Transactional
    @Override
    public RestaurantTo create(Restaurant restaurant) {
        return asTo(restaurantRepository.create(restaurant));
    }

    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    @Transactional
    @Override
    public void delete(int id) {
        restaurantRepository.delete(id);
    }

    @Override
    public RestaurantTo get(int id) {
        return asTo(restaurantRepository.get(id));
    }

    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    @Transactional
    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.update(restaurant);
    }

    @Override
    @Cacheable("restaurants")
    public Page<RestaurantTo> getPage(int page) {
        return asTo(restaurantRepository.getPage(page));
    }

    @Override
    public Page<RestaurantWithMenuTo> getPageWithMenu(int page, LocalDate date){
        Page<RestaurantTo> restaurants = getPage(page);
        List<Integer> id = restaurants.stream().map(r -> r.getId()).collect(toList());
        List<Menu> menu = menuRepository.getByRestaurantId(id, date);
        return asToWithMenu(restaurants, menu);
    }

    @Cacheable("restaurants_with_menu")
    public Page<RestaurantWithMenuTo> getPageOnlyWithMenu(int page, LocalDate date){
        return asToWithMenu(menuRepository.getPage(page, date));
    }
}
