package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Role;
import com.herokuapp.mivoto.model.User;
import java.util.Arrays;

import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "Ivanov", "ivanov@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Petrov", "petrov@gmail.com", "password", Role.ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}