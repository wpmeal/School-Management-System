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
import com.wpmeal.school.domain.courses.Course;
import com.wpmeal.school.domain.courses.Lecture;
import com.wpmeal.school.domain.courses.Result;
import com.wpmeal.school.domain.courses.repositories.AssignmentRepository;
import com.wpmeal.school.domain.courses.repositories.CourseRepository;
import com.wpmeal.school.domain.courses.repositories.LectureRepository;
import com.wpmeal.school.domain.courses.repositories.ResultRepository;
import com.wpmeal.school.domain.users.Student;
import com.wpmeal.school.domain.users.Teacher;
import com.wpmeal.school.domain.users.repositories.StudentRepository;
import com.wpmeal.school.domain.users.repositories.TeacherRepository;

@RepositoryRestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private LectureRepository lectureRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private ResultRepository resultRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/teachers/{teacherId}")
	public ResponseEntity<Resource<Course>> assignTeacherToCourse(@PathVariable int id, @PathVariable int teacherId) {
		Teacher teacher = teacherRepository.findOne(teacherId);
		Course course = courseRepository.findOne(id);
		if (teacher != null) {
			course.addTeacherToCourse(course, teacher);
			courseRepository.save(course);
			Resource<Course> resource = new Resource<Course>(course);
			resource.add(ControllerLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Course>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/students/{studentId}")
	public ResponseEntity<Resource<Course>> assignStudentToCourse(@PathVariable int id, @PathVariable int studentId) {
		Student student = studentRepository.findOne(studentId);
		Course course = courseRepository.findOne(id);
		if (student != null) {
			course.addStudentToCourse(student);
			courseRepository.save(course);
			Resource<Course> resource = new Resource<Course>(course);
			resource.add(ControllerLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Course>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/lectures/{lectureId}")
	public ResponseEntity<Resource<Course>> assignLectureToCourse(@PathVariable int id, @PathVariable int lectureId) {
		Lecture lecture = lectureRepository.findOne(lectureId);
		Course course = courseRepository.findOne(id);
		if (lecture != null) {
			course.addLectureToCourse(lecture);
			courseRepository.save(course);
			Resource<Course> resource = new Resource<Course>(course);
			resource.add(ControllerLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Course>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/lectures/{lectureId}")
	public ResponseEntity<Resource<Course>> deleteAssignedLecture(@PathVariable int id, @PathVariable int lectureId) {
		Lecture lecture = lectureRepository.findOne(lectureId);
		Course course = courseRepository.findOne(id);
		if (lecture != null) {
			course.removeLecturefromCourse(lecture);
			courseRepository.save(course);
			return new ResponseEntity<Resource<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/students/{studentId}")
	public ResponseEntity<Resource<Course>> deleteAssignedStudent(@PathVariable int id, @PathVariable int studentId) {
		Student student = studentRepository.findOne(studentId);
		Course course = courseRepository.findOne(id);
		if (student != null) {
			course.removeStudentfromCourse(student);
			courseRepository.save(course);
			return new ResponseEntity<Resource<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/teachers/{teacherId}")
	public ResponseEntity<Resource<Course>> deleteAssignedTeacher(@PathVariable int id, @PathVariable int teacherId) {
		Teacher teacher = teacherRepository.findOne(teacherId);
		Course course = courseRepository.findOne(id);
		if (teacher != null) {
			course.removeTeacherfromCourse(teacher);
			courseRepository.save(course);
			return new ResponseEntity<Resource<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/assignments/{assignmentId}")
	public ResponseEntity<Resource<Course>> assignAssigmnetToCourse(@PathVariable int id,
			@PathVariable int assignmentId) {
		Assignment assignment = assignmentRepository.findOne(assignmentId);
		Course course = courseRepository.findOne(id);
		if (assignment != null) {
			course.addAssignmentToCourse(assignment);
			courseRepository.save(course);
			Resource<Course> resource = new Resource<Course>(course);
			resource.add(ControllerLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Course>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/assignments/{assignmentId}")
	public ResponseEntity<Resource<Course>> deleteAssignedAssignment(@PathVariable int id,
			@PathVariable int assignmentId) {
		Assignment assignment = assignmentRepository.findOne(assignmentId);
		Course course = courseRepository.findOne(id);
		if (assignment != null) {
			course.removeAssignmenFromCourse(assignment);
			courseRepository.save(course);
			return new ResponseEntity<Resource<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/results/{resultId}")
	public ResponseEntity<Resource<Course>> assignResultToCourse(@PathVariable int id, @PathVariable int resultId) {
		Result result = resultRepository.findOne(resultId);
		Course course = courseRepository.findOne(id);
		if (result != null) {
			course.addResultToCourse(result);
			courseRepository.save(course);
			Resource<Course> resource = new Resource<Course>(course);
			resource.add(ControllerLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel());
			return new ResponseEntity<Resource<Course>>(resource, HttpStatus.OK);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/results/{resultId}")
	public ResponseEntity<Resource<Course>> deleteAssignedResult(@PathVariable int id, @PathVariable int resultId) {
		Result result = resultRepository.findOne(resultId);
		Course course = courseRepository.findOne(id);
		if (result != null) {
			course.removeResultFromCourse(result);
			courseRepository.save(course);
			return new ResponseEntity<Resource<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Resource<Course>>(HttpStatus.NOT_FOUND);
	}

}
