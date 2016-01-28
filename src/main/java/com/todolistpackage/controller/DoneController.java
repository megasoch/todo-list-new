package com.todolistpackage.controller;

import com.todolistpackage.entity.Done;
import com.todolistpackage.entity.User;
import com.todolistpackage.repository.DoneRepository;
import com.todolistpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by megasoch on 25.12.2015.
 */
@RestController
@RequestMapping("/done-tasks")
public class DoneController {

    @Autowired
    DoneRepository doneRepository;

    @Autowired
    UserRepository userRepository;

    public User currentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Done createTask(@RequestBody Done done) {
        done.setUser(currentUser());
        return doneRepository.save(done);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Done> tasks() {
        return doneRepository.findByUser(currentUser(), new Sort(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        doneRepository.delete(id);
    }
}
