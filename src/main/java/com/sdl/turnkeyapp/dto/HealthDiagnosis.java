package com.sdl.turnkeyapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HealthDiagnosis {

  @JsonProperty("Issue")
  private DiagnosedIssue Issue;
  @JsonProperty("Specialisation")
  private List<DiagnosedSpecialisation> Specialisation;
}
