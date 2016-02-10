package com.todolistpackage.controller;

import com.todolistpackage.entity.User;
import com.todolistpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User userInput) throws NoSuchAlgorithmException {
        User user = new User(userInput.getUsername(), userInput.getPassword());
        Long id = userRepository.save(user).getId();
        if (id == 0) {
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
