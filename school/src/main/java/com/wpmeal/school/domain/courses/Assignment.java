package com.wpmeal.school.domain.courses;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assignmentId;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private LocalDate deadLine;
    
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	private List<Result> results;


	public List<Result> getResult() {
		return results;
	}

	public void setResult(List<Result> result) {
		this.results = result;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public void addResultToAssignment(Result result) {
		results.add(result);
	}

	public void removeResultFromAssignment(Result result) {
		results.remove(result);
	}
}
