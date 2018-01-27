package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Role;
import com.herokuapp.mivoto.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.List;

import static com.herokuapp.mivoto.UserTestData.*;
import static com.herokuapp.mivoto.UserTestData.assertMatch;

public  class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        User newUser = new User(null, "new", "new@gmail.com", "newPass", Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}