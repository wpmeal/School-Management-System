package com.wpmeal.school.domain.courses.repositories;


import org.springframework.data.repository.CrudRepository;
import com.wpmeal.school.domain.courses.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {

}
