package com.herokuapp.mivoto;

import com.herokuapp.mivoto.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.herokuapp.mivoto.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH_ID = START_SEQ + 15; //next after 2 users, 13 restaurants
    public static final Dish SPAGHETTI = new Dish(DISH_ID, "British crab spaghetti", new BigDecimal("14.75") , LocalDate.of(2017,12,30));
    public static final Dish LASAGNE = new Dish(DISH_ID + 1, "Oxtail Lasagne", new BigDecimal("13.95"), LocalDate.of(2017,12,30));
    public static final Dish TIRAMISU = new Dish(DISH_ID + 2, "Tiramisu", new BigDecimal("5.95"), LocalDate.of(2017,12,30));
    public static final Dish CANNELLONI = new Dish(DISH_ID + 3, "Squash Cannelloni", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish CARBONARA = new Dish(DISH_ID + 4, "The carbonara", new BigDecimal("11.95"), LocalDate.of(2017,12,30));
    public static final Dish RAVIOLI = new Dish(DISH_ID + 5, "Sausage Ravioli", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish CHICKEN = new Dish(DISH_ID + 6, "Sicilian chicken", new BigDecimal("14.95"), LocalDate.of(2017,12,30));
    public static final Dish POTATO_FRIES = new Dish(DISH_ID + 7, "Sweet potato fries", new BigDecimal("4.00"), LocalDate.of(2017,12,30));
    public static final Dish THE_PORKIE = new Dish(DISH_ID + 8, "The Porkie", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish THE_FUNGHI = new Dish(DISH_ID + 9, "The Funghi", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish THE_TUSCAN = new Dish(DISH_ID + 10, "The Tuscan", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish THE_MEETBALL = new Dish(DISH_ID + 11, "The Meetball", new BigDecimal("12.95"), LocalDate.of(2017,12,30));
    public static final Dish THE_JULIETTA = new Dish(DISH_ID + 12, "The Julietta", new BigDecimal("11.95"), LocalDate.of(2017,12,30));
    public static final Dish PUMPKIN_ARANCINI = new Dish(DISH_ID + 13, "Pumpkin arancini", new BigDecimal("5.95"), LocalDate.of(2017,12,30));
    public static final Dish MUSHROOM_FRITTI = new Dish(DISH_ID + 14, "Mushroom fritti", new BigDecimal("5.95"), LocalDate.of(2017,12,30));
    public static final Dish ITALIAN_MEATBALLS = new Dish(DISH_ID + 15, "Italian meatballs", new BigDecimal("6.25"), LocalDate.of(2017,12,30));
    public static final Dish MIXED_GRILL = new Dish(DISH_ID + 16, "Gennaro's mixed grill", new BigDecimal("19.95"), LocalDate.of(2017,12,30));
}