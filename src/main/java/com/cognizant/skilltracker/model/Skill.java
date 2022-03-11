package com.cognizant.skilltracker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
	private String skill;
	private String expertise;
	private String skillType;

}
