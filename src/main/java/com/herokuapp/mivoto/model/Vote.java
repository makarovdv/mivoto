package com.herokuapp.mivoto.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "votes_unique_date_user_idx")})
public class Vote extends AbstractBaseEntity {
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vote() {}

    public Vote(LocalDate date, Restaurant restaurant, User user) {
        this(null, date, restaurant, user);
    }

    public Vote(Integer id, LocalDate date, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
