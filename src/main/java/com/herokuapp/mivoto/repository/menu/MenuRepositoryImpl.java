package com.herokuapp.mivoto.repository.menu;

import com.herokuapp.mivoto.model.Menu;

import com.herokuapp.mivoto.repository.restaurant.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    @Autowired
    private CrudMenuRepository menuRepository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    private int PAGE_SIZE = 10;

    @Override
    public Menu save(Menu menu, Integer restaurantId) {
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        if (menu.isNew()) {
            return menuRepository.save(menu);
        } else{
            return (get(menu.getId()) == null) ? null : menuRepository.save(menu);
        }
    }

    @Override
    public boolean delete(int id) {
        return menuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Menu> getPage(int page, LocalDate date) {
        return menuRepository.findAll(PageRequest.of(page,PAGE_SIZE), date);
    }

    @Override
    public List<Menu> getByRestaurantIds(List<Integer> id, LocalDate date) {
        return menuRepository.getByRestaurantIds(id, date);
    }

    @Override
    public Menu getByRestaurantId(Integer restaurantId, LocalDate date) {
        return menuRepository.getByRestaurantId(restaurantId, date);
    }
}
