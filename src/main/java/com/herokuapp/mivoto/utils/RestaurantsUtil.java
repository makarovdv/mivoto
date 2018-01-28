package com.herokuapp.mivoto.utils;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RestaurantsUtil {

    private RestaurantsUtil(){}

    public static Restaurant fromTo(RestaurantTo restaurant) {
        return new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhone());
    }

    public static RestaurantTo asTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhone());
    }

    public static PageTo<RestaurantTo> asTo(Page<Restaurant> restaurants) {
        List<RestaurantTo> content =  restaurants.stream()
                .map(r -> asTo(r)).collect(Collectors.toList());
        Pageable p = restaurants.getPageable();
        return new PageTo<>(content, p.getPageNumber(), p.getPageSize(), restaurants.getTotalPages());
    }

    public static RestaurantWithMenuTo asToWithMenu(RestaurantTo restaurant, MenuTo menu) {
        return new RestaurantWithMenuTo(restaurant, menu);
    }

    public static RestaurantWithMenuTo asToWithMenu(Restaurant restaurant, Menu menu) {
        return new RestaurantWithMenuTo(asTo(restaurant), MenuUtil.asTo(menu));
    }

    public static PageTo<RestaurantWithMenuTo> asToWithMenu(Page<Menu> menu) {
        List<RestaurantWithMenuTo> content = menu.stream()
                .map(m -> asToWithMenu(m.getRestaurant(), m))
                .collect(toList());
        Pageable p = menu.getPageable();
        return new PageTo(content, p.getPageNumber(), p.getPageSize(), menu.getTotalPages());
    }

    public static PageTo<RestaurantWithMenuTo> asToWithMenu(PageTo<RestaurantTo> restaurants, List<Menu> menu) {
        List<Integer> id = restaurants.stream()
                .map(r -> r.getId())
                .collect(toList());
        Map<Integer, MenuTo> menuMap = menu.stream()
                .collect(Collectors.toMap(m -> m.getRestaurant().getId(), MenuUtil::asTo));
        List<RestaurantWithMenuTo> content = restaurants.stream()
                .map(r -> asToWithMenu(r, menuMap.get(r.getId())))
                .collect(toList());
        return new PageTo<>(content, restaurants.getPage(), restaurants.getPageSize(), restaurants.getTotalPages());
    }
}
