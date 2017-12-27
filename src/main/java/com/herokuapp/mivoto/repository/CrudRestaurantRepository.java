package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{
    Sort sort = new Sort(Sort.Direction.ASC, "name", "address", "phone");

    Restaurant getById(Integer id);

    List<Restaurant> findAll(Sort sort);

    default List<Restaurant> getAll(){
        return findAll(sort);
    }
}