package com.herokuapp.mivoto.web;

import com.herokuapp.mivoto.AuthorizedUser;
import com.herokuapp.mivoto.service.*;
import com.herokuapp.mivoto.to.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController {
    static final String REST_URL = "/rest/user";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private MenuService menuService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/votes/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public void voteFor(@RequestParam int id) {
        LocalDate now = getCurrentDate();
        log.info("vote for restaurant {} date {}", id, now);
        voteService.vote(id, AuthorizedUser.id(), getCurrentTime(), now);
    }

    @GetMapping(value = "/restaurants/menu/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageTo<RestaurantWithMenuTo> getRestaurantPageWithMenu(@RequestParam(name = "page", defaultValue = "0") int page) {
        LocalDate now = getCurrentDate();
        log.info("get restaurants with menu page {} for date {}", page, now);
        return restaurantService.getPageOnlyWithMenu(page, now);
    }

    @GetMapping(value = "/menu/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo getMenu(@RequestParam("restaurantId") int restaurantId) {
        LocalDate now = getCurrentDate();
        log.info("get menu restaurantId {} for date {}", restaurantId, now);
        return menuService.get(now, restaurantId);
    }

    @GetMapping(value = "/restaurants/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageTo<RestaurantTo> getRestaurantPage(@RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("get restaurants page {}", page);
        return restaurantService.getPage(page);
    }

    protected LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    protected LocalTime getCurrentTime(){
        return LocalTime.now();
    }
}
