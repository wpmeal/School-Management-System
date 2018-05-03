package com.wpmeal.school.domain.courses;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;
import com.wpmeal.school.domain.users.Student;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resultId;
	
	private double grade;

	@NotEmpty(message = "Evaluation should be set to one of these values 'COMPLETED, NOT_COMPLETED, PASS, FAIL'")
	private String evaluation;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	private Student student;
	

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		
		for (Evaluation evaluateVal : Evaluation.values()) {
			
			if (evaluateVal.toString().equals(evaluation)) {
				this.evaluation = evaluation;
			}
		}
	}

}
