package com.wpmeal.school.domain.courses.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wpmeal.school.domain.courses.Course;

@RepositoryRestResource
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

}
