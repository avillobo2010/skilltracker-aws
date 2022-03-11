package com.cognizant.skilltracker.repository;

import com.cognizant.skilltracker.data.FseDocument;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@EnableScan
public interface FseProfileRepository extends CrudRepository<FseDocument,String> {


//	Optional<FseDocument> findByUserId(String userId);
}
