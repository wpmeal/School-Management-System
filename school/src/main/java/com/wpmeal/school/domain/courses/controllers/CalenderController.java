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

import com.wpmeal.school.domain.courses.Calender;
import com.wpmeal.school.domain.courses.Lecture;
import com.wpmeal.school.domain.courses.repositories.CalenderRepository;
import com.wpmeal.school.domain.courses.repositories.LectureRepository;

@RepositoryRestController
@RequestMapping("/calenders")
public class CalenderController {

	@Autowired
	private CalenderRepository calenderRepository;

	@Autowired
	private LectureRepository lectureRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/lectures/{lectureId}")
	public ResponseEntity<Resource<Calender>> assignLectureToCalender(@PathVariable int id,
			@PathVariable int lectureId) {
		Lecture lecture = lectureRepository.findOne(lectureId);
		Calender calender = calenderRepository.findOne(id);
		if (lecture != null) {
			calender.setLecture(lecture);
			calenderRepository.save(calender);
			Resource<Calender> resource = new Resource<Calender>(calender);
			resource.add(ControllerLinkBuilder.linkTo(CalenderController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Calender>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Calender>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/lectures/{lectureId}")
	public ResponseEntity<Resource<Calender>> removeLectureFromCalender(@PathVariable int id,
			@PathVariable int lectureId) {
		Lecture lecture = lectureRepository.findOne(lectureId);
		Calender calender = calenderRepository.findOne(id);
		if (lecture != null) {
			calender.setLecture(null);
			calenderRepository.save(calender);
			return new ResponseEntity<Resource<Calender>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Calender>>(HttpStatus.NOT_FOUND);
	}

}
