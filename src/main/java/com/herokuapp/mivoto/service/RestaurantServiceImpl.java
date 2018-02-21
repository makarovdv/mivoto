package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.repository.menu.MenuRepository;
import com.herokuapp.mivoto.repository.restaurant.RestaurantRepository;
import com.herokuapp.mivoto.to.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.herokuapp.mivoto.utils.RestaurantsUtil.asTo;
import static com.herokuapp.mivoto.utils.RestaurantsUtil.asToWithMenu;
import static com.herokuapp.mivoto.utils.ValidationUtil.checkNotFoundWithId;
import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    @Override
    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    public RestaurantTo create(Restaurant restaurant) {
        return asTo(restaurantRepository.create(restaurant));
    }

    @Transactional
    @Override
    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public RestaurantTo get(int id) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(id),  id);
        return asTo(restaurant);
    }

    @Transactional
    @Override
    @CacheEvict(value = {"restaurants", "restaurants_with_menu"}, allEntries = true)
    public void update(Restaurant restaurant) {
        checkNotFoundWithId(restaurantRepository.update(restaurant), restaurant.getId());
    }

    @Override
    @Cacheable("restaurants")
    public PageTo<RestaurantTo> getPage(int page) {
        return asTo(restaurantRepository.getPage(page));
    }

    @Override
    public PageTo<RestaurantWithMenuTo> getPageWithMenu(int page, LocalDate date){
        PageTo<RestaurantTo> restaurants = getPage(page);
        List<Integer> ids = restaurants
                .getContent()
                .stream()
                .map(BaseTo::getId).collect(toList());
        List<Menu> menu = menuRepository.getByRestaurantIds(ids, date);
        return asToWithMenu(restaurants, menu);
    }

    @Cacheable("restaurants_with_menu")
    public PageTo<RestaurantWithMenuTo> getPageOnlyWithMenu(int page, LocalDate date){
        return asToWithMenu(menuRepository.getPage(page, date));
    }
}
