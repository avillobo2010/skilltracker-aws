package com.cognizant.skilltracker.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.cognizant.skilltracker.producer.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.skilltracker.error.ErrorResponse;
import com.cognizant.skilltracker.data.FseDocument;
import com.cognizant.skilltracker.data.SkillDocument;
import com.cognizant.skilltracker.error.ErrorModel;
import com.cognizant.skilltracker.model.FseProfile;
import com.cognizant.skilltracker.model.Skill;

import com.cognizant.skilltracker.service.FseProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/api/v1")
public class SkillTrackerController {

	@Autowired
	FseProfileService profileService;

	@Autowired
	RabbitMQSender rabbitMQSender;
	


	private static final String ERROR_MESSAGE = "Invalid Skill Expertise";

	@PostMapping("/engineer/add-profil")
	public ResponseEntity<String> addProfile(@Valid @RequestBody FseProfile profile) {

		if (validateProfileSkills(profile.getSkills())) {
			return new ResponseEntity<String>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
		}
		String userID = profileService.addProfile(profile);
		return new ResponseEntity<String>(userID, HttpStatus.OK);
	}

	@PostMapping("/engineer/update-profile/{userId}")
	public ResponseEntity<String> addProfile(@PathVariable("userId") String userId, @RequestBody List<Skill> skills) {

		if (validateProfileSkills(skills)) {
			return new ResponseEntity<String>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
		}
		FseDocument document = profileService.getProfile(userId).orElse(null);
		if (document == null) {
			return new ResponseEntity<String>("UserId Invalid Or Profile Not Found", HttpStatus.NOT_FOUND);
		}
		if (Duration.between(LocalDateTime.now(), document.getLastUpdatedDateTime()).toDays() > -10) {
			return new ResponseEntity<String>("Profile Update Allowed Only After 10 Days", HttpStatus.OK);
		}
		for (SkillDocument skill : document.getSkills()) {
			Skill updatedSkill = skills.stream().filter(s -> s.getSkill().equalsIgnoreCase(skill.getSkill()))
					.findFirst().get();
			if (StringUtils.isNumeric(updatedSkill.getExpertise())) {
				skill.setExpertise(Integer.parseInt(updatedSkill.getExpertise()));
			}
		}
		document.setLastUpdatedDateTime(LocalDateTime.now());
		//profileService.updateProfile(document);
		rabbitMQSender.send(document);
		return new ResponseEntity<String>(userId, HttpStatus.OK);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(MethodArgumentNotValidException exception) {

		List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
				.map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage())).distinct()
				.collect(Collectors.toList());
		return ErrorResponse.builder().errorMessage(errorMessages).build();
	}

	private boolean validateProfileSkills(List<Skill> skills) {
		return skills.stream().anyMatch(s -> !(StringUtils.isNumeric(s.getExpertise())
				&& (Integer.parseInt(s.getExpertise()) >= 0 && Integer.parseInt(s.getExpertise()) <= 20)));

	}

	/*@PostMapping("/admin/name/{profileName}/id/{associateId}/skill/{skillName}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<FseProfile> getProfiles(@PathVariable("profileName") String profileName,
			@PathVariable("associateId") String associateId, @PathVariable("skillName") String skillName) {

		return profileService.getProfiles(profileName, associateId, skillName);

	}*/

}
