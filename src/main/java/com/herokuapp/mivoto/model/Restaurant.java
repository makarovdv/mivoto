package com.herokuapp.mivoto.model;

public class Restaurant extends AbstractNamedEntity {

    public Restaurant(){}

    public Restaurant(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public Restaurant(Integer id, String name, String address, String phone) {
        super(id, name);
        this.address = address;
        this.phone = phone;
    }

    private String address;

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
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
