package com.herokuapp.mivoto.utils;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.to.MenuTo;

public class MenuUtil {
    private MenuUtil() {}

    public static MenuTo asTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDate(), menu.getDishes(), menu.getRestaurant().getId());
    }

    public static Menu fromTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getDate(), menuTo.getDishes());
    }
}
