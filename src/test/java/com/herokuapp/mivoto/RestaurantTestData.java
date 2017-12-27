package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Restaurant;
import java.util.Arrays;

import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;

    public static final Restaurant TERRA_MARE = new Restaurant(RESTAURANT1_ID,"TerraMare", "Tsvetnoi Blvd., 20/1, Moscow 127051, Russia","+74956081519");
    public static final Restaurant SALOTTO = new Restaurant(RESTAURANT1_ID + 1,"Salotto","Staropimenovskiy Ln., 11/6, Moscow 125009, Russia","+79100000920");
    public static final Restaurant SICILIANA = new Restaurant(RESTAURANT1_ID + 2,"La Bottega Siciliana","Okhotny Ryad, 2, Moscow 125009, Russia","+74956600383");
    public static final Restaurant UPDATED_TERRA_MARE = new Restaurant(RESTAURANT1_ID,"updated", "Tsvetnoi Blvd., 20/1, Moscow 127051, Russia","+74956081519");

    public static final Restaurant[] RESTAURANTS = new Restaurant[]{SICILIANA, SALOTTO, TERRA_MARE};

    public static void assertMatch(Restaurant actual, Restaurant expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
}
