package com.cognizant.skilltracker.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FseProfile {

	@NotBlank
	@Size(min = 5, max = 30, message = "Name cannot be empty and should have minimum 5 and maximum 30 Characters")
	private String name;

	@NotBlank
	@Size(min = 5, max = 30)
	@Pattern(regexp = "^CTS.*$", message = "Id Cannot Be Empty and Should Start With CTS")
	private String id;

	@NotBlank
	@Size(min = 10, max = 10, message = "No Cannot be empty. Should have 10 numbers")
	private String mobile;

	@NotBlank
	@Email(message = "Invalid Email")
	private String email;

	private LocalDateTime createdDateTime;
	private LocalDateTime lastUpdatedDateTime;

	private List<Skill> skills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

}
