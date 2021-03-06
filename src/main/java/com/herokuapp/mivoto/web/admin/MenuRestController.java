package com.herokuapp.mivoto.web.admin;

import com.herokuapp.mivoto.service.MenuService;
import com.herokuapp.mivoto.to.MenuTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(MenuRestController.REST_URL)
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo get(@PathVariable("id") int id) {
        log.info("get menu {}", id);
        return menuService.get(id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo get(@RequestParam("date") LocalDate date, @RequestParam("restaurantId") Integer restaurantId) {
        log.info("get menu by date{}, restaurantId", date, restaurantId);
        return menuService.get(date, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuTo> create(@Valid @RequestBody MenuTo menu) {
        log.info("create menu {}", menu);
        checkNew(menu);
        MenuTo created = menuService.create(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody MenuTo menu, @PathVariable("id") int id) {
        log.info("update menu {} with id {}", menu, id);
        assureIdConsistent(menu, id);
        menuService.update(menu);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") int id) {
        log.info("delete menu {}", id);
        menuService.delete(id);
    }
}
