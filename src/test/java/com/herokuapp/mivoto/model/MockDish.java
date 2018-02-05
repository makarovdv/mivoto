package com.herokuapp.mivoto.model;

import java.math.BigDecimal;

public class MockDish {
    private Dish dish;

    public MockDish(Dish dish){
        this.dish = dish;
    }

    public BigDecimal getPrice() {
        return dish.getPrice();
    }

    public String getDescription(){
        return dish.getDescription();
    }

    @Override
    public int hashCode() {
        return dish.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockDish that = (MockDish)o;
        return getDescription().equals(that.getDescription())&&getPrice().equals(that.getPrice());
    }
}
