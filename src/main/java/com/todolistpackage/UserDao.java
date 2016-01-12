package com.todolistpackage;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by megasoch on 24.12.2015.
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
