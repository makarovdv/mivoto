package com.herokuapp.mivoto.web;

import com.herokuapp.mivoto.service.RestaurantService;
import com.herokuapp.mivoto.to.PageTo;
import com.herokuapp.mivoto.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(CommonRestController.REST_URL)
public class CommonRestController {
    static final String REST_URL = "/rest/common/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageTo<RestaurantTo> getRestaurantPageWithMenu(@RequestParam("page") int page) {
        log.info("get restaurants page {}", page);
        return restaurantService.getPage(page);
    }
}
