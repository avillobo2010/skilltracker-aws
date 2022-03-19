package com.cognizant.skilltracker.model;

import com.cognizant.skilltracker.data.FseDocument;
import com.cognizant.skilltracker.data.SkillDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FseSkillTracker implements Serializable {
   private  FseDocument fseDocument;
   private  List<SkillDocument> skillDocumentList;
}
