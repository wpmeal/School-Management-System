package com.wpmeal.school.domain.users.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wpmeal.school.domain.users.Student;

@RepositoryRestResource
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
