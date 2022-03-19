package com.cognizant.skilltracker.repository;

import com.cognizant.skilltracker.data.SkillDocument;
import org.springframework.data.repository.CrudRepository;

public interface FseSkillRepository extends CrudRepository<SkillDocument,String> {
}