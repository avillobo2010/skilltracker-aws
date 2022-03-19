package com.cognizant.skilltracker.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cognizant.skilltracker.repository.FseSkillRepository;
import com.cognizant.skilltracker.repository.SkillSearchRepository;
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

    @Autowired
    FseSkillRepository profileSkillRepository;

	@Autowired
	SkillSearchRepository skillSearchRepository;

    public String addProfile(FseProfile fseProfile) {
        FseDocument document = FseDocument.builder().associateName(fseProfile.getName()).associateId(fseProfile.getId())
                .mobile(fseProfile.getMobile()).email(fseProfile.getEmail())
                .createdDateTime(LocalDateTime.now()).lastUpdatedDateTime(LocalDateTime.now())
                .build();
        profileRepository.save(document);
        List<SkillDocument> skills = fseProfile.getSkills().stream().map(
                                     skill -> { return convertToSkillDocument(skill, document.getUserid()); }
		                               ).collect(Collectors.toList());
        profileSkillRepository.saveAll(skills);
        return document.getUserid();

    }

    public Optional<FseDocument> getProfile(String userId) {
		return profileRepository.findById(userId);

    }

    public List<SkillDocument> getSkills(String userId) {
        return skillSearchRepository.getFseSkillByCriteria(userId);

    }

    private SkillDocument convertToSkillDocument(Skill skill, String userid) {
        SkillDocument skillDocument = new SkillDocument();
        skillDocument.setExpertise(Integer.parseInt(skill.getExpertise()));
        skillDocument.setSkillName(skill.getSkill());
        skillDocument.setSkillType(skill.getSkillType());
        skillDocument.setUserid(userid);
        return skillDocument;
    }

}
