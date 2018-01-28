package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.to.PageTo;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.to.RestaurantWithMenuTo;

import java.util.*;

import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static com.herokuapp.mivoto.utils.RestaurantsUtil.asTo;
import static org.assertj.core.api.Assertions.assertThat;


public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2; // next after 2 users

    public static final Restaurant TERRA_MARE = new Restaurant(RESTAURANT1_ID,"TerraMare", "Tsvetnoi Blvd., 20/1","+74956081519");
    public static final Restaurant SALOTTO = new Restaurant(RESTAURANT1_ID + 1,"Salotto","Staropimenovskiy Ln., 11/6","+79100000920");
    public static final Restaurant SICILIANA = new Restaurant(RESTAURANT1_ID + 2,"La Bottega Siciliana","Okhotny Ryad, 2","+74956600383");
    public static final Restaurant BOSCO_CAFE = new Restaurant(RESTAURANT1_ID + 3,"Bosco Cafe","Krasnaya Sq., 3","+74956203182");
    public static final Restaurant DOLKABAR = new Restaurant(RESTAURANT1_ID + 4,"Dolkabar","Krasina St., 7","+74992547908");
    public static final Restaurant OSTERIA_ALBOROBELLO = new Restaurant(RESTAURANT1_ID + 5,"Osteria Alberobello","Leninskiy Ave., 75A","+74991343524");
    public static final Restaurant POROSELLO = new Restaurant(RESTAURANT1_ID + 6,"Porosello","Lubyanskiy Drive, 25/2","+74956235969");
    public static final Restaurant PASTA_AND_BASTA = new Restaurant(RESTAURANT1_ID + 7,"Pasta and Basta","Sretenskiy bulvar, 4 | Metro Chistye Prudi","+74956245252");
    public static final Restaurant OSTERIA_MARIO = new Restaurant(RESTAURANT1_ID + 8,"Osteria Mario","Baltiyskaya St., 9","+74957907090");
    public static final Restaurant COFFEE_ROOM = new Restaurant(RESTAURANT1_ID + 9,"Coffee Room","Arbat St., 13","+74956973553");
    public static final Restaurant FORTE_BELLO = new Restaurant(RESTAURANT1_ID + 10,"Forte Bello","Mezhdunarodnaya St., 18 | Mall Vegas Crocus City","+74952361072");
    public static final Restaurant DONNA_MARGATITA = new Restaurant(RESTAURANT1_ID + 11,"Donna Margarita","1905 Goda St., 2A","+74996827000");
    public static final Restaurant BOSCO_BAR = new Restaurant(RESTAURANT1_ID + 12,"Bosco Bar","Krasnaya Sq., 3","+74956273703");

    public static final Restaurant UPDATED_TERRA_MARE = new Restaurant(RESTAURANT1_ID,"updated", "Tsvetnoi Blvd., 20/1","+74956081519");

    public static final Restaurant[] RESTAURANTS = new Restaurant[]{BOSCO_BAR, BOSCO_CAFE, COFFEE_ROOM, DOLKABAR, DONNA_MARGATITA, FORTE_BELLO, SICILIANA, OSTERIA_ALBOROBELLO, OSTERIA_MARIO, PASTA_AND_BASTA, POROSELLO, SALOTTO, TERRA_MARE};
    public static final Restaurant[] RESTAURANTS_PAGE1 = new Restaurant[]{BOSCO_BAR, BOSCO_CAFE, COFFEE_ROOM, DOLKABAR, DONNA_MARGATITA, FORTE_BELLO, SICILIANA, OSTERIA_ALBOROBELLO, OSTERIA_MARIO, PASTA_AND_BASTA};
    public static final Restaurant[] RESTAURANTS_PAGE2 = new Restaurant[]{POROSELLO, SALOTTO, TERRA_MARE};
    public static final RestaurantWithMenuTo[] RESTAURANTS_WITH_MENU_PAGE2 = new RestaurantWithMenuTo[]{
            new RestaurantWithMenuTo(asTo(POROSELLO), MENU7),
            new RestaurantWithMenuTo(asTo(SALOTTO), MENU2),
            new RestaurantWithMenuTo(asTo(TERRA_MARE), MENU1)
    };
    public static final PageTo<RestaurantWithMenuTo> RESTAURANTS_WITH_MENU_PAGE2TO = new PageTo<>(Arrays.asList(RESTAURANTS_WITH_MENU_PAGE2),2, 10, 2);

    private static Comparator<RestaurantWithMenuTo> RESTAURANT_WITH_MENU_COMPARATOR = (r1, r2) -> {
        int id = Integer.compare(r1.getId(), r2.getId());
        if (id!=0) return id;
        int address = r1.getAddress().compareTo(r1.getAddress());
        if (address!=0) return address;
        int phone = r1.getPhone().compareTo(r1.getPhone());
        if (phone!=0) return phone;
        return MENU_TO_COMPARATOR.compare(r1.getMenu(), r2.getMenu());
    };

    public static Restaurant getCreated(){
        return new Restaurant(null, "Via Romano", "Lavochkina St., 34, Moscow 125581, Russia", "+74955453480");
    }

    public static void assertMatch(RestaurantTo actual, Restaurant expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<RestaurantTo> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<RestaurantTo> actual, Restaurant... expected){
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatchWithMenu(Iterable<RestaurantWithMenuTo> actual, Iterable<RestaurantWithMenuTo> expected){
        assertThat(actual).usingElementComparator(RESTAURANT_WITH_MENU_COMPARATOR)
        .isEqualTo(expected);
    }

    public static void assertMatchWithMenu(Iterable<RestaurantWithMenuTo> actual, RestaurantWithMenuTo... expected){
        assertMatchWithMenu(actual, Arrays.asList(expected));
    }
}
