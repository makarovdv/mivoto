package com.herokuapp.mivoto.repository;

import com.herokuapp.mivoto.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
    Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Query("DELETE FROM User u WHERE u.id=:id")
    @Modifying
    int delete(@Param("id") int id);

    default User createOrUpdate(User user){
        if (user.isNew()) {
            return save(user);
        } else {
            return get(user.getId()) != null ? save(user) : null;
        }
    }

    default User get(int id){
        return findById(id).orElse(null);
    }

    default List<User> getAll(){
        return findAll(SORT_NAME_EMAIL);
    }

    User getByEmail(String email);
}
