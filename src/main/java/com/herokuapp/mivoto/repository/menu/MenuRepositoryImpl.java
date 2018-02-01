package com.herokuapp.mivoto.repository.menu;

import com.herokuapp.mivoto.model.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    @Autowired
    private CrudMenuRepository repository;

    private int PAGE_SIZE = 10;

    @Override
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            return repository.save(menu);
        } else{
            return (get(menu.getId()) == null) ? null : repository.save(menu);
        }
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Menu> getPage(int page, LocalDate date) {
        return repository.findAll(PageRequest.of(page,PAGE_SIZE), date);
    }

    @Override
    public List<Menu> getByRestaurantId(List<Integer> id, LocalDate date) {
        return repository.getByRestaurantId(id, date);
    }

    @Override
    public Menu get(LocalDate date, Integer restaurantId) {
        return repository.getByDateAndRestaurantId(date, restaurantId);
    }
}
