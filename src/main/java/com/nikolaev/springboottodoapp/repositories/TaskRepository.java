package com.nikolaev.springboottodoapp.repositories;

import com.nikolaev.springboottodoapp.entities.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nikolaev Artem
 **/

public interface TaskRepository extends CrudRepository<Task, Long> {
}
