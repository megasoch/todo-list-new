package com.todolistpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by megasoch on 25.12.2015.
 */
@RestController
@RequestMapping("/done-tasks")
public class DoneController {

    @Autowired
    DoneDao doneDao;

    @RequestMapping(method = RequestMethod.POST)
    public Done createTask(@RequestBody Done done) {
        Done newDone = new Done(done.getUserId(), done.getTitle());
        return doneDao.save(newDone);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Done> tasks() {
        return (List<Done>) doneDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        doneDao.delete(id);
    }
}
