package com.sdl.turnkeyapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiagnosedSpecialisation {


  @JsonProperty("SpecialistID")
  private Integer specialistId;

  @JsonProperty("ID")
  private int id;

  @JsonProperty("Name")
  private String name;


  @JsonProperty("Accuracy")
  private float accuracy;

  @JsonProperty("Ranking")
  private String ranking;
}
