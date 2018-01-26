package com.herokuapp.mivoto.to;

public class RestaurantWithMenuTo extends RestaurantTo{
    private MenuTo menu;

    public RestaurantWithMenuTo(Integer id, String name, String address, String phone, MenuTo menu) {
        super(id, name, address, phone);
        this.menu = menu;
    }

    public RestaurantWithMenuTo(RestaurantTo restaurantTo, MenuTo menu) {
        super(restaurantTo);
        this.menu = menu;
    }

    public MenuTo getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "RestaurantWithMenuTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", menu=" + menu +
                '}';
    }
}
