package com.wpmeal.school.domain.courses;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.ResourceSupport;
import com.wpmeal.school.domain.users.Student;
import com.wpmeal.school.domain.users.Teacher;

@Entity
public class Course extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int courseId;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime startDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime endDate;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Teacher> teachers; 

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Student> students;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Lecture> lectures;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Assignment> assignments;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Result> results;

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Set<Result> getResult() {
		return results;
	}

	public void setResult(Set<Result> result) {
		this.results = result;
	}

	public Set<Assignment> getAssignment() {
		return assignments;
	}

	public void setAssignment(Set<Assignment> assignment) {
		this.assignments = assignment;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Set<Teacher>  getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teacher) {
		this.teachers = teacher;
	}

	// Convenience Method
	public void addTeacherToCourse(Course course,Teacher teacher) {
		//if(teachers.ge == null )
		teachers.add(teacher);
		// }
	}

	public void addStudentToCourse(Student student) {
		students.add(student);
	}

	public void addLectureToCourse(Lecture lecture) {
		lectures.add(lecture);
	}
	
	public void addAssignmentToCourse(Assignment assignment) {
		assignments.add(assignment);
	}

	public void removeLecturefromCourse(Lecture lecture) {
		lectures.remove(lecture);
	}

	public void removeStudentfromCourse(Student student) {
		students.remove(student);
	}

	public void removeTeacherfromCourse(Teacher teacher) {
		teachers.remove(teacher);
	}
	public void removeAssignmenFromCourse(Assignment assignment) {
		assignments.remove(assignment);
	}
	
	public void addResultToCourse(Result result) {
		results.add(result);
	}
	
	public void removeResultFromCourse(Result result) {
		results.remove(result);
	}
}
