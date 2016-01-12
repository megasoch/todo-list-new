package com.todolistpackage;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by megasoch on 25.12.2015.
 */

@Transactional
public interface TaskDao extends CrudRepository<Task, Long> {

}
