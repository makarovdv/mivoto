package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.model.Menu;
import com.herokuapp.mivoto.repository.CrudMenuRepository;
import com.herokuapp.mivoto.to.MenuTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import static com.herokuapp.mivoto.utils.MenuUtil.asTo;
import static com.herokuapp.mivoto.utils.MenuUtil.fromTo;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    private final CrudMenuRepository repository;

    @Autowired
    public MenuServiceImpl(CrudMenuRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public MenuTo create(MenuTo menu) {
        Menu saved = repository.save(fromTo(menu));
        return asTo(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public void update(MenuTo menu) {
        repository.save(fromTo(menu));
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurants_with_menu", allEntries = true)
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public MenuTo get(int id) {
        return asTo(repository.getById(id));
    }

    @Override
    public MenuTo get(LocalDate date, Integer restaurantId) {
        return asTo(repository.getByDateAndRestaurantId(date, restaurantId));
    }
}
