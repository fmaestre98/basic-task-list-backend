package com.basictasklist.basictasklist.repositories;

import com.basictasklist.basictasklist.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task,Long> {
}
