package com.herokuapp.mivoto.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "menu_unique_restaurant_and_date_idx")})
public class Menu extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    private LocalDate date;

    @BatchSize(size = 200)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dishes", joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
    private Set<Dish> dishes;

    public Menu() {}

    public Menu(Integer id) {
        super(id);
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDate(), menu.getDishes());
    }

    public Menu(LocalDate date, Set<Dish> dishes) {
        this.date = date;
        this.dishes = dishes;
    }

    public Menu(Integer id, LocalDate date, Set<Dish> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", id=" + id +
                ", dishes=\n" + dishes.toString().replaceAll("},", "},\n") +
                '}';
    }
}
