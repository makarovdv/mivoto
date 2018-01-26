package com.herokuapp.mivoto;

import com.herokuapp.mivoto.to.MenuTo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import static com.herokuapp.mivoto.DishTestData.*;
import static com.herokuapp.mivoto.DishTestData.CARBONARA;
import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;
import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 15; //next after 2 users, 13 restaurants

    public static final MenuTo MENU1 = new MenuTo(MENU1_ID, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(SPAGHETTI, LASAGNE, TIRAMISU)), RESTAURANT1_ID);
    public static final MenuTo MENU2 = new MenuTo(MENU1_ID + 1, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(CANNELLONI, CARBONARA)), RESTAURANT1_ID + 1);
    public static final MenuTo MENU3 = new MenuTo(MENU1_ID + 2, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(RAVIOLI, CHICKEN)), RESTAURANT1_ID + 2);
    public static final MenuTo MENU4 = new MenuTo(MENU1_ID + 3, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(POTATO_FRIES)), RESTAURANT1_ID + 3);
    public static final MenuTo MENU5 = new MenuTo(MENU1_ID + 4, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(THE_PORKIE)), RESTAURANT1_ID + 4);
    public static final MenuTo MENU6 = new MenuTo(MENU1_ID + 5, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(THE_FUNGHI)), RESTAURANT1_ID + 5);
    public static final MenuTo MENU7 = new MenuTo(MENU1_ID + 6, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(THE_TUSCAN)), RESTAURANT1_ID + 6);
    public static final MenuTo MENU8 = new MenuTo(MENU1_ID + 7, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(THE_MEETBALL)), RESTAURANT1_ID + 7);
    public static final MenuTo MENU9 = new MenuTo(MENU1_ID + 8, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(THE_JULIETTA)), RESTAURANT1_ID + 8);
    public static final MenuTo MENU10 = new MenuTo(MENU1_ID + 9, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(PUMPKIN_ARANCINI)), RESTAURANT1_ID + 9);
    public static final MenuTo MENU11 = new MenuTo(MENU1_ID + 10, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(MUSHROOM_FRITTI)), RESTAURANT1_ID + 10);
    public static final MenuTo MENU12 = new MenuTo(MENU1_ID + 11, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(ITALIAN_MEATBALLS)), RESTAURANT1_ID + 11);
    public static final MenuTo MENU13 = new MenuTo(MENU1_ID + 12, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(MIXED_GRILL)), RESTAURANT1_ID + 12);
    public static final MenuTo MENU14 = new MenuTo(MENU1_ID + 13, LocalDate.of(2017,12,31), new HashSet<>(Arrays.asList(AUBERGIN)), RESTAURANT1_ID);

    public static final MenuTo UPDATED_MENU1 = new MenuTo(MENU1_ID, LocalDate.of(2017,12,31), new HashSet<>(Arrays.asList(SPAGHETTI, LASAGNE, TIRAMISU, MUSHROOM_FRITTI)), RESTAURANT1_ID + 1);

    public static Comparator<MenuTo> MENU_TO_COMPARATOR = (m1, m2) -> {
        int date = m1.getDate().compareTo(m2.getDate());
        if (date!=0) return date;
        int restaurantId = Integer.compare(m1.getRestaurantId(), m2.getRestaurantId());
        if (restaurantId!=0) return restaurantId;
        return m1.getDishes().toString().compareTo(m2.getDishes().toString());
    };

    public static MenuTo getCreated(){
        return new MenuTo(null, LocalDate.of(2017,12,31), new HashSet<>(Arrays.asList(CANNELLONI, CARBONARA)), RESTAURANT1_ID + 1);
    }

    public static void assertMatch(MenuTo actual, MenuTo expected){
        assertThat(actual).usingComparator(MENU_TO_COMPARATOR).isEqualToComparingFieldByField(expected);
    }
}
