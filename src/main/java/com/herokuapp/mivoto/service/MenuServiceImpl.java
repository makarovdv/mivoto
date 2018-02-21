package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.repository.menu.MenuRepository;
import com.herokuapp.mivoto.to.MenuTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import static com.herokuapp.mivoto.utils.MenuUtil.asTo;
import static com.herokuapp.mivoto.utils.MenuUtil.fromTo;
import static com.herokuapp.mivoto.utils.ValidationUtil.checkNotFound;
import static com.herokuapp.mivoto.utils.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository repository;

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public MenuTo create(MenuTo menu) {
        Menu saved = repository.save(fromTo(menu), menu.getRestaurantId());
        return asTo(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public void update(MenuTo menu) {
        checkNotFoundWithId(repository.save(fromTo(menu), menu.getRestaurantId()), menu.getId());
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true) // assume removal is rare
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public MenuTo get(int id) {
        Menu menu = checkNotFoundWithId(repository.get(id), id);
        return asTo(menu);
    }

    @Override
    public MenuTo get(LocalDate date, Integer restaurantId) {
        Menu menu = repository.getByRestaurantId(restaurantId, date);
        checkNotFound(menu!=null, String.format("restaurantId: %d, for date: %tF", restaurantId, date));
        return asTo(menu);
    }
}
