package com.cognizant.skilltracker.service;

import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cognizant.skilltracker.data.FseDocument;
import com.cognizant.skilltracker.data.SkillDocument;
import com.cognizant.skilltracker.model.FseProfile;
import com.cognizant.skilltracker.model.Skill;
import com.cognizant.skilltracker.repository.FseProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FseProfileService {

	@Autowired
	FseProfileRepository profileRepository;

	public String addProfile(FseProfile fseProfile) {
		FseDocument document = FseDocument.builder().name(fseProfile.getName()).id(fseProfile.getId())
				.mobile(fseProfile.getMobile()).email(fseProfile.getEmail())
				.skills(fseProfile.getSkills().stream().map(this::convertToSkillDocument).collect(Collectors.toList()))
				.createdDateTime(LocalDateTime.now()).lastUpdatedDateTime(LocalDateTime.now())
			.build();
		profileRepository.save(document);
		return document.getUserid();

	}

	public Optional<FseDocument> getProfile(String userId) {
		return profileRepository.findById(userId);

	}

	public String updateProfile(FseDocument document) {
		profileRepository.save(document);
		return document.getUserid();
	}

	/*public List<FseProfile> getProfiles(String name, String id, String skill) {

		String nameregex = "^" + name;
		List<FseDocument> documents = profileRepository.getFseDocumentByCriteria(nameregex, id, skill);
		return documents.stream().map(this::convertToFseProfile)
				.filter(profile -> !CollectionUtils.isEmpty(profile.getSkills())).collect(Collectors.toList());
	}*/

	private FseProfile convertToFseProfile(FseDocument document) {
		FseProfile profile = new FseProfile();
		profile.setName(document.getName());
		profile.setId(document.getId());
		profile.setMobile(document.getMobile());
		profile.setEmail(document.getEmail());
		profile.setLastUpdatedDateTime(document.getLastUpdatedDateTime());
		profile.setCreatedDateTime(document.getCreatedDateTime());
		profile.setSkills(document.getSkills().stream().filter(s -> s.getExpertise() >= 10)
				.sorted(Comparator.comparingInt(SkillDocument::getExpertise)).map(this::convertToSkill)
				.collect(Collectors.toList()));
		return profile;
	}

	private SkillDocument convertToSkillDocument(Skill skill) {
		SkillDocument skillDocument = new SkillDocument();
		skillDocument.setExpertise(Integer.parseInt(skill.getExpertise()));
		skillDocument.setSkill(skill.getSkill());
		skillDocument.setSkillType(skill.getSkillType());
		return skillDocument;
	}

	private Skill convertToSkill(SkillDocument skillDocument) {
		Skill skill = new Skill();
		skill.setExpertise(String.valueOf(skillDocument.getExpertise()));
		skill.setSkill(skillDocument.getSkill());
		skill.setSkillType(skillDocument.getSkillType());
		return skill;
	}
}
