package com.herokuapp.mivoto.repository.restaurant;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{

    @Modifying
    @Query("UPDATE Restaurant r SET r.name=?1, r.address=?2, r.phone=?3 WHERE r.id=?4")
    int update(String name, String address, String phone, Integer id);

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    int delete(@Param("id") int id);
}