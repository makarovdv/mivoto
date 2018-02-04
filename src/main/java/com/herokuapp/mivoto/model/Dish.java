package com.herokuapp.mivoto.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class Dish implements Serializable{
    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(min = 2, max = 100)
    private String description;

    public Dish() {}

    public Dish(Dish dish) {
        this(dish.getDescription(), dish.getPrice());
    }

    public Dish(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish that = (Dish) o;
        return getDescription().equals(that.getDescription());
    }
    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }
}