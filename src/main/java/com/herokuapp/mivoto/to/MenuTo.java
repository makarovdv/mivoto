package com.herokuapp.mivoto.to;

import com.herokuapp.mivoto.model.Dish;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;

public class MenuTo extends BaseTo{
    private LocalDate date;

    @Valid
    private Set<Dish> dishes;

    private Integer restaurantId;

    public MenuTo() {}

    public MenuTo(MenuTo m) {
        this(m.getId(), m.getDate(), m.getDishes(), m.getRestaurantId());
    }

    public MenuTo(Integer id, LocalDate date, Set<Dish> dishes, Integer restaurantId) {
        super(id);
        this.date = date;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", date=" + date +
                ", dishes=" + dishes +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
