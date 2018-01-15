package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{
    Sort sort = new Sort(Sort.Direction.ASC, "name", "address", "phone");

    Restaurant getById(Integer id);

    List<Restaurant> findAll(Sort sort);

    default List<Restaurant> getAll(){
        return findAll(sort);
    }

    default Page<Restaurant> getPage(int page, LocalDate date){
        return findAll(PageRequest.of(page - 1,10, sort), date);
    }

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menu m WHERE m.date = :date")
    Page<Restaurant> findAll(Pageable pageable, @Param("date")LocalDate date);
}