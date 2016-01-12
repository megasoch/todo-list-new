package com.todolistpackage;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by megasoch on 25.12.2015.
 */
public interface DoneDao extends CrudRepository<Done, Long> {

}
