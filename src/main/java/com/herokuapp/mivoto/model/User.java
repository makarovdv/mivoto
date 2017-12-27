package com.herokuapp.mivoto.model;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    public User() {
    }

    public User(Integer id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public User(User user){
        super(user.id, user.name);
        this.email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
