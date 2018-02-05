package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Dish;
import com.herokuapp.mivoto.model.MockDish;
import com.herokuapp.mivoto.to.MenuTo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.herokuapp.mivoto.DishTestData.*;
import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANT1_ID;
import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 15; //next after 2 users, 13 restaurants

    public static final MenuTo MENU1 = new MenuTo(MENU1_ID, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(SPAGHETTI, LASAGNE, TIRAMISU)), RESTAURANT1_ID);
    public static final MenuTo MENU2 = new MenuTo(MENU1_ID + 1, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(CANNELLONI, CARBONARA)), RESTAURANT1_ID + 1);
    public static final MenuTo MENU3 = new MenuTo(MENU1_ID + 2, LocalDate.of(2017,12,30), new HashSet<>(Arrays.asList(RAVIOLI, CHICKEN)), RESTAURANT1_ID + 2);
    public static final MenuTo MENU4 = new MenuTo(MENU1_ID + 3, LocalDate.of(2017,12,30), Collections.singleton(POTATO_FRIES), RESTAURANT1_ID + 3);
    public static final MenuTo MENU5 = new MenuTo(MENU1_ID + 4, LocalDate.of(2017,12,30), Collections.singleton(THE_PORKIE), RESTAURANT1_ID + 4);
    public static final MenuTo MENU6 = new MenuTo(MENU1_ID + 5, LocalDate.of(2017,12,30), Collections.singleton(THE_FUNGHI), RESTAURANT1_ID + 5);
    public static final MenuTo MENU7 = new MenuTo(MENU1_ID + 6, LocalDate.of(2017,12,30), Collections.singleton(THE_TUSCAN), RESTAURANT1_ID + 6);
    public static final MenuTo MENU8 = new MenuTo(MENU1_ID + 7, LocalDate.of(2017,12,30), Collections.singleton(THE_MEETBALL), RESTAURANT1_ID + 7);
    public static final MenuTo MENU9 = new MenuTo(MENU1_ID + 8, LocalDate.of(2017,12,30), Collections.singleton(THE_JULIETTA), RESTAURANT1_ID + 8);
    public static final MenuTo MENU10 = new MenuTo(MENU1_ID + 9, LocalDate.of(2017,12,30), Collections.singleton(PUMPKIN_ARANCINI), RESTAURANT1_ID + 9);
    public static final MenuTo MENU11 = new MenuTo(MENU1_ID + 10, LocalDate.of(2017,12,30), Collections.singleton(MUSHROOM_FRITTI), RESTAURANT1_ID + 10);
    public static final MenuTo MENU12 = new MenuTo(MENU1_ID + 11, LocalDate.of(2017,12,30), Collections.singleton(ITALIAN_MEATBALLS), RESTAURANT1_ID + 11);
    public static final MenuTo MENU13 = new MenuTo(MENU1_ID + 12, LocalDate.of(2017,12,30), Collections.singleton(MIXED_GRILL), RESTAURANT1_ID + 12);
    public static final MenuTo MENU14 = new MenuTo(MENU1_ID + 13, LocalDate.of(2017,12,31), Collections.singleton(AUBERGIN), RESTAURANT1_ID);

    public static final MenuTo UPDATED_MENU1 = new MenuTo(MENU1_ID, LocalDate.of(2017,12,31), new HashSet<>(Arrays.asList(SPAGHETTI, LASAGNE, TIRAMISU, MUSHROOM_FRITTI)), RESTAURANT1_ID + 1);

    public static Comparator<MenuTo> MENU_TO_COMPARATOR = new Comparator<MenuTo>() {
        @Override
        public int compare(MenuTo m1, MenuTo m2) {
            int result;
            if ((result = m1.getDate().compareTo(m2.getDate()))!=0) return result;
            if ((result = Integer.compare(m1.getRestaurantId(), m2.getRestaurantId()))!=0) return result;
            return convertToMock(m1.getDishes()).equals(convertToMock(m2.getDishes())) ? 0 : 1;
        }

        private Set<MockDish> convertToMock(Set<Dish> dishes){
            return dishes.stream().map(MockDish::new).collect(Collectors.toSet());
        }
    };

    public static MenuTo getCreated(){
        return new MenuTo(null, LocalDate.of(2017,12,31), new HashSet<>(Arrays.asList(CANNELLONI, CARBONARA)), RESTAURANT1_ID + 1);
    }

    public static void assertMatch(MenuTo actual, MenuTo expected){
        assertThat(actual).usingComparator(MENU_TO_COMPARATOR).isEqualToComparingFieldByField(expected);
    }
}
