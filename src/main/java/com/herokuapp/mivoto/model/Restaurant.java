package com.herokuapp.mivoto.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Menu> menu;

    public Restaurant(){}

    public Restaurant(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public Restaurant(Integer id){
        super(id);
    }

    public Restaurant(Integer id, String name, String address, String phone) {
        this(id, name, address, phone, Collections.emptySet());
    }
    public Restaurant(Integer id, String name, String address, String phone, Set<Menu> menu) {
        super(id, name);
        this.address = address;
        this.phone = phone;
        this.menu = menu;
    }

    private String address;

    private String phone;

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", menu=" + (menu == null ? "null" : menu) +
                '}';
    }
}
