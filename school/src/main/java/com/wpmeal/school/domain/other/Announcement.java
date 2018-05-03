package com.wpmeal.school.domain.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.wpmeal.school.domain.courses.Course;

@Entity
public class Announcement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int announcementId;
	private String content;
	
	@ManyToOne
	private Course course;

	public int getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
