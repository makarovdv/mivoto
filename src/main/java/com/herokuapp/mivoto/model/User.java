package com.herokuapp.mivoto.model;

import org.springframework.util.CollectionUtils;
import java.util.*;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Collection<Role> roles) {
        this(id, name, email);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public User(User u){
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRoles());
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }
}
