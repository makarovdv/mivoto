package com.herokuapp.mivoto.service;

import com.herokuapp.mivoto.AuthorizedUser;
import com.herokuapp.mivoto.model.User;
import com.herokuapp.mivoto.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final CrudUserRepository repository;

    @Autowired
    public UserServiceImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id){
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Transactional
    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}