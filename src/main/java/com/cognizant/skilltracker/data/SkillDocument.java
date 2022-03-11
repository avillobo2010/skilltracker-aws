package com.cognizant.skilltracker.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
@Builder
public class SkillDocument {

	@DynamoDBAttribute
	private String skill;

	@DynamoDBAttribute
	private Integer expertise;

	@DynamoDBAttribute
	private String skillType;

}
