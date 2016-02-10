package com.todolistpackage.repository;

import com.todolistpackage.entity.Done;
import com.todolistpackage.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoneRepository extends CrudRepository<Done, Long> {
    List<Done> findByUser(User user, Sort sort);
}
