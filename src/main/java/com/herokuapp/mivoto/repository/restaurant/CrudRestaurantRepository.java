package com.herokuapp.mivoto.repository.restaurant;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    int delete(@Param("id") int id);
}