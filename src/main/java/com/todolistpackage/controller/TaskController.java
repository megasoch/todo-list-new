package com.todolistpackage.controller;

import com.todolistpackage.entity.Task;
import com.todolistpackage.entity.User;
import com.todolistpackage.repository.TaskRepository;
import com.todolistpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public User currentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task createTask(@RequestBody Task task) {
        task.setUser(currentUser());
        return taskRepository.save(task);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> tasks() {
        return taskRepository.findByUser(currentUser(), new Sort(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        taskRepository.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task update(@RequestBody @Valid Task task) {
        task.setUser(currentUser());
        return taskRepository.save(task);
    }
}
