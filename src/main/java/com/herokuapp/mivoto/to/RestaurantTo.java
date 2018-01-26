package com.herokuapp.mivoto.to;

import com.herokuapp.mivoto.HasId;

public class RestaurantTo extends BaseTo{
    protected String name;
    protected String address;
    protected String phone;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public RestaurantTo(RestaurantTo restaurantTo) {
        this(restaurantTo.id, restaurantTo.name, restaurantTo.address, restaurantTo.phone);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
