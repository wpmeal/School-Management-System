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

import com.wpmeal.school.domain.courses.Assignment;
import com.wpmeal.school.domain.courses.Result;
import com.wpmeal.school.domain.courses.repositories.AssignmentRepository;
import com.wpmeal.school.domain.courses.repositories.ResultRepository;

@RepositoryRestController
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private ResultRepository resultRepository;	

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/results/{resultId}")
	public ResponseEntity<Resource<Assignment>> assignResultToAssignment(@PathVariable int id, @PathVariable int resultId) {
		Result result = resultRepository.findOne(resultId);
		Assignment assignment = assignmentRepository.findOne(id);
		if (result != null) {
			assignment.addResultToAssignment(result);
			assignmentRepository.save(assignment);
			Resource<Assignment> resource = new Resource<Assignment>(assignment);
			resource.add(ControllerLinkBuilder.linkTo(AssignmentController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Assignment>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Assignment>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/results/{resultId}")
	public ResponseEntity<Resource<Assignment>> deleteAssignedResult(@PathVariable int id, @PathVariable int resultId) {
		Result result = resultRepository.findOne(resultId);
		Assignment assignment = assignmentRepository.findOne(id);
		if (result != null) {
			assignment.removeResultFromAssignment(result);
			assignmentRepository.save(assignment);
			return new ResponseEntity<Resource<Assignment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Assignment>>(HttpStatus.NOT_FOUND);
	}
}
