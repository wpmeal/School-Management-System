package com.wpmeal.school.domain.courses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int lectureId;
	
	@NotEmpty
	private String title;
	private String Content;
	
	@NotEmpty
	private String room;


	public int getLectureId() {
		return lectureId;
	}

	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
