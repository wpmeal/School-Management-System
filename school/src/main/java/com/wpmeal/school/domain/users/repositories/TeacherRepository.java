package com.wpmeal.school.domain.users.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.wpmeal.school.domain.users.Teacher;

@RepositoryRestResource
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

}
