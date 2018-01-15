package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Restaurant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2; // next after 2 users

    public static final Restaurant TERRA_MARE = new Restaurant(RESTAURANT1_ID,"TerraMare", "Tsvetnoi Blvd., 20/1","+74956081519", new HashSet<>(Arrays.asList(MENU1)));
    public static final Restaurant SALOTTO = new Restaurant(RESTAURANT1_ID + 1,"Salotto","Staropimenovskiy Ln., 11/6","+79100000920", new HashSet<>(Arrays.asList(MENU2)));
    public static final Restaurant SICILIANA = new Restaurant(RESTAURANT1_ID + 2,"La Bottega Siciliana","Okhotny Ryad, 2","+74956600383", new HashSet<>(Arrays.asList(MENU3)));
    public static final Restaurant BOSCO_CAFE = new Restaurant(RESTAURANT1_ID + 3,"Bosco Cafe","Krasnaya Sq., 3","+74956203182", new HashSet<>(Collections.singleton(MENU4)));
    public static final Restaurant DOLKABAR = new Restaurant(RESTAURANT1_ID + 4,"Dolkabar","Krasina St., 7","+74992547908", new HashSet<>(Collections.singleton(MENU5)));
    public static final Restaurant OSTERIA_ALBOROBELLO = new Restaurant(RESTAURANT1_ID + 5,"Osteria Alberobello","Leninskiy Ave., 75A","+74991343524", new HashSet<>(Collections.singleton(MENU6)));
    public static final Restaurant POROSELLO = new Restaurant(RESTAURANT1_ID + 6,"Porosello","Lubyanskiy Drive, 25/2","+74956235969", new HashSet<>(Collections.singleton(MENU7)));
    public static final Restaurant PASTA_AND_BASTA = new Restaurant(RESTAURANT1_ID + 7,"Pasta and Basta","Sretenskiy bulvar, 4 | Metro Chistye Prudi","+74956245252", new HashSet<>(Collections.singleton(MENU8)));
    public static final Restaurant OSTERIA_MARIO = new Restaurant(RESTAURANT1_ID + 8,"Osteria Mario","Baltiyskaya St., 9","+74957907090", new HashSet<>(Collections.singleton(MENU9)));
    public static final Restaurant COFFEE_ROOM = new Restaurant(RESTAURANT1_ID + 9,"Coffee Room","Arbat St., 13","+74956973553", new HashSet<>(Collections.singleton(MENU10)));
    public static final Restaurant FORTE_BELLO = new Restaurant(RESTAURANT1_ID + 10,"Forte Bello","Mezhdunarodnaya St., 18 | Mall Vegas Crocus City","+74952361072", new HashSet<>(Collections.singleton(MENU11)));
    public static final Restaurant DONNA_MARGATITA = new Restaurant(RESTAURANT1_ID + 11,"Donna Margarita","1905 Goda St., 2A","+74996827000", new HashSet<>(Collections.singleton(MENU12)));
    public static final Restaurant BOSCO_BAR = new Restaurant(RESTAURANT1_ID + 12,"Bosco Bar","Krasnaya Sq., 3","+74956273703", new HashSet<>(Collections.singleton(MENU13)));

    public static final Restaurant UPDATED_TERRA_MARE = new Restaurant(RESTAURANT1_ID,"updated", "Tsvetnoi Blvd., 20/1","+74956081519", new HashSet<>(Arrays.asList(MENU1)));

    public static final Restaurant[] RESTAURANTS = new Restaurant[]{BOSCO_BAR, BOSCO_CAFE, COFFEE_ROOM, DOLKABAR, DONNA_MARGATITA, FORTE_BELLO, SICILIANA, OSTERIA_ALBOROBELLO, OSTERIA_MARIO, PASTA_AND_BASTA, POROSELLO, SALOTTO, TERRA_MARE};
    public static final Restaurant[] RESTAURANTS_WITHOUT_SALOTTO = new Restaurant[]{BOSCO_BAR, BOSCO_CAFE, COFFEE_ROOM, DOLKABAR, DONNA_MARGATITA, FORTE_BELLO, SICILIANA, OSTERIA_ALBOROBELLO, OSTERIA_MARIO, PASTA_AND_BASTA, POROSELLO, TERRA_MARE};
    public static final Restaurant[] PAGE1_RESTAURANTS = new Restaurant[]{BOSCO_BAR, BOSCO_CAFE, COFFEE_ROOM, DOLKABAR, DONNA_MARGATITA, FORTE_BELLO, SICILIANA, OSTERIA_ALBOROBELLO, OSTERIA_MARIO, PASTA_AND_BASTA};
    public static final Restaurant[] PAGE2_RESTAURANTS = new Restaurant[]{POROSELLO, SALOTTO, TERRA_MARE};

    public static void assertMatch(Restaurant actual, Restaurant expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
}
