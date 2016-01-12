package com.todolistpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by megasoch on 21.12.2015.
 */
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody User userInput) throws NoSuchAlgorithmException {
        User user = new User(userInput.getUsername(), Password.passwordHash(userInput.getPassword(), 1000));
        userDao.save(user);
        return "OK";
    }
}
