package com.sdl.turnkeyapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiagnosedIssue {

  @JsonProperty("ID")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Icd")
  private String icd;
@JsonProperty("IcdName")
  private String icdName;

  @JsonProperty("ProfName")
  private String profName;

  @JsonProperty("Accuracy")
  private float accuracy;

  @JsonProperty("Ranking")
  private float ranking;
}
