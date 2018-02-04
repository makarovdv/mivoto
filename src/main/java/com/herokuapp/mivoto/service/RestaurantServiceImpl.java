package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.repository.menu.MenuRepository;
import com.herokuapp.mivoto.repository.restaurant.RestaurantRepository;
import com.herokuapp.mivoto.to.BaseTo;
import com.herokuapp.mivoto.to.PageTo;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CacheEvictionService cacheEvictionService;

    @Transactional
    @Override
    public RestaurantTo create(Restaurant restaurant) {
        cacheEvictionService.evictAll();
        return asTo(restaurantRepository.create(restaurant));
    }

    @Transactional
    @Override
    public void delete(int id) {
        cacheEvictionService.evictAll();
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public RestaurantTo get(int id) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(id),  id);
        return asTo(restaurant);
    }

    @Transactional
    @Override
    public void update(Restaurant restaurant) {
        cacheEvictionService.evictAll();
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
        List<Integer> id = restaurants
                .getContent()
                .stream()
                .map(BaseTo::getId).collect(toList());
        List<Menu> menu = menuRepository.getByRestaurantId(id, date);
        return asToWithMenu(restaurants, menu);
    }

    @Cacheable(value = "restaurants_with_menu", key="#date.toString().concat(#page)")
    public PageTo<RestaurantWithMenuTo> getPageOnlyWithMenu(int page, LocalDate date){
        cacheEvictionService.addKeyToSet(date.toString() + page);
        return asToWithMenu(menuRepository.getPage(page, date));
    }
}
