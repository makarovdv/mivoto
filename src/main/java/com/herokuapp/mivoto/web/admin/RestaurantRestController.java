package com.herokuapp.mivoto.web.admin;

import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.service.RestaurantService;
import com.herokuapp.mivoto.to.PageTo;
import com.herokuapp.mivoto.to.RestaurantTo;

import com.herokuapp.mivoto.to.RestaurantWithMenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static com.herokuapp.mivoto.utils.ValidationUtil.assureIdConsistent;
import static com.herokuapp.mivoto.utils.ValidationUtil.checkNew;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> create(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        RestaurantTo created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }


    @GetMapping(value = "/menu/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageTo<RestaurantWithMenuTo> getRestaurantPageWithMenu(@RequestParam("page") int page, @RequestParam("date") LocalDate date) {
        return restaurantService.getPageWithMenu(page, date);
    }
}
