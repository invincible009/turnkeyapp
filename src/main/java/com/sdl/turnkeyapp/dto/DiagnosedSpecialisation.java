package com.sdl.turnkeyapp.dto;

import lombok.Data;

@Data
public class DiagnosedSpecialisation extends HealthItem {

  private Integer specialistId;

  private float accuracy;

  private String ranking;
}
