package com.todolistpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by megasoch on 25.12.2015.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskDao taskDao;

    @RequestMapping(method = RequestMethod.POST)
    public Task createTask(@RequestBody Task task) {
        Task newTask = new Task(task.getUserId(), task.getTitle());
        return taskDao.save(newTask);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> tasks() {
        return (List<Task>) taskDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        taskDao.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable("id") Long id, @RequestBody @Valid Task task) {
        return taskDao.save(task);
    }
}
