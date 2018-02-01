package com.herokuapp.mivoto.utils;

import com.herokuapp.mivoto.HasId;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new IllegalArgumentException("Not found entity with " + msg);
        }
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id: " + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFound(object != null, "id: " + id);
        return object;
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}