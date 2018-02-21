package com.herokuapp.mivoto.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurants_unique_name")})
public class Restaurant extends AbstractNamedEntity {

    public Restaurant(){}

    public Restaurant(Integer id){
        super(id);
    }

    public Restaurant(Integer id, String name, String address, String phone) {
        super(id, name);
        this.address = address;
        this.phone = phone;
    }

    @Size(max = 100)
    private String address;

    @Size(max = 50)
    private String phone;

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
                '}';
    }
}
