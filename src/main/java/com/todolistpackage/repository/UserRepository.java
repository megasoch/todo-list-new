package com.todolistpackage.repository;

import com.todolistpackage.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by megasoch on 24.12.2015.
 */

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
