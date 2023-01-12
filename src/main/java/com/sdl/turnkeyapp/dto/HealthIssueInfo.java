package com.sdl.turnkeyapp.dto;

import lombok.Data;

@Data
public class HealthIssueInfo {

  private String name;

  private String profName;

  private String descriptionShort;

  private String description;

  private String medicalCondition;

  private String treatmentDescription;

  private String synonyms;

  private String possibleSymptoms;
}
