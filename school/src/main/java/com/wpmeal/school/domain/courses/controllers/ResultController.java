package com.wpmeal.school.domain.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wpmeal.school.domain.courses.Result;
import com.wpmeal.school.domain.courses.repositories.ResultRepository;
import com.wpmeal.school.domain.users.Student;
import com.wpmeal.school.domain.users.repositories.StudentRepository;

@RepositoryRestController
@RequestMapping("/results")
public class ResultController {

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/student/{studentId}")
	public ResponseEntity<Resource<Result>> assignResultToAssignment(@PathVariable int id,
			@PathVariable int studentId) {
		Student student = studentRepository.findOne(studentId);
		Result result = resultRepository.findOne(id);
		if (student != null) {
			result.setStudent(student);
			resultRepository.save(result);
			Resource<Result> resource = new Resource<Result>(result);
			resource.add(ControllerLinkBuilder.linkTo(ResultController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Result>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Result>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/student/{studentId}")
	public ResponseEntity<Resource<Result>> removeStudentFromResult(@PathVariable int id, @PathVariable int studentId) {
		Student student = studentRepository.findOne(studentId);
		Result result = resultRepository.findOne(id);
		if (student != null) {
			result.setStudent(null);
			resultRepository.save(result);
			return new ResponseEntity<Resource<Result>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Result>>(HttpStatus.NOT_FOUND);
	}
}
