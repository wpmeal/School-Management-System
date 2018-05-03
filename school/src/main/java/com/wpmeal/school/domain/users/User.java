package com.wpmeal.school.domain.users;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uId;

	@Size(min = 5, max = 10)
	@NotEmpty
	private String userName;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty(message = "Gender must be set to 'M' or 'F'")
	private String gender;

    @DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private LocalDate birth;

	@Size(min = 5, max = 20)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		for (Gender genderVal : Gender.values()) {
			if (genderVal.toString().equals(gender)) {
				this.gender = gender;
			}
		}
	}

	public enum Gender {
		M, F
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
