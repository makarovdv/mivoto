package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer>{

    Menu getById(Integer id);

    default Page<Menu> getPage(int page, LocalDate date){
        return findAll(PageRequest.of(page,10), date);
    }

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m LEFT JOIN m.restaurant WHERE m.date = :date")
    Page<Menu> findAll(Pageable pageable, @Param("date")LocalDate date);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id IN ?1 AND m.date = ?2")
    List<Menu> getByRestaurantId(List<Integer> id, LocalDate date);

    Menu getByDateAndRestaurantId(LocalDate date, Integer restaurantId);
}
