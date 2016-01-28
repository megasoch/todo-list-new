package com.todolistpackage.repository;

import com.todolistpackage.entity.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by megasoch on 25.12.2015.
 */

@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByUser(User user, Sort sort);
}
