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
import static com.herokuapp.mivoto.utils.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository repository;

    @Autowired
    private CacheEvictionService cacheEvictionService;

    @Override
    @Transactional
    public MenuTo create(MenuTo menu) {
        Menu saved = repository.save(fromTo(menu));
        cacheEvictionService.evict(menu.getDate().toString());
        return asTo(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public void update(MenuTo menu) {
        checkNotFoundWithId(repository.save(fromTo(menu)), menu.getId());
        cacheEvictionService.clearKeys();
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true) // assume removal is rare
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
        cacheEvictionService.clearKeys();
    }

    @Override
    public MenuTo get(int id) {
        Menu menu = checkNotFoundWithId(repository.get(id), id);
        return asTo(menu);
    }

    @Override
    public MenuTo get(LocalDate date, Integer restaurantId) {
        return asTo(repository.get(date, restaurantId));
    }
}
