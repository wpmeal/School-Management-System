package com.wpmeal.school.domain.courses;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Calender {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int calenderId;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime startDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime endDate;

	@ManyToOne
	private Lecture lecture;	

	private String dayOfWeek;

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

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {

		for (DayOfWeek dayOfWeekVal : DayOfWeek.values()) {

			if (dayOfWeekVal.toString().equals(dayOfWeek)) {
				this.dayOfWeek = dayOfWeek;
			}
		}
	}	
}
