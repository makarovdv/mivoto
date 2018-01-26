package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{

    Restaurant getById(Integer id);

    @Query("SELECT r FROM Restaurant r")
    Page<Restaurant> findAll(Pageable pageable);
}