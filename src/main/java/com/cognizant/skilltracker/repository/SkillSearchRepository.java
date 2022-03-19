package com.cognizant.skilltracker.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cognizant.skilltracker.data.SkillDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SkillSearchRepository {
  @Autowired
  AmazonDynamoDB amazonDynamoDB;

  public List<SkillDocument> getFseSkillByCriteria(String userid) {

        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(userid));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userid = :v1")
                 .withExpressionAttributeValues(eav);
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        List<SkillDocument> skillDocuments = mapper.scan(SkillDocument.class, scanExpression);
        return skillDocuments;

    }
}
