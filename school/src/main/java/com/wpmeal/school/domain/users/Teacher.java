package com.wpmeal.school.domain.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.wpmeal.school.domain.courses.Course;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="uId")

public class Teacher extends User {
    
	@ManyToMany
	private Set<Course> courses;

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
