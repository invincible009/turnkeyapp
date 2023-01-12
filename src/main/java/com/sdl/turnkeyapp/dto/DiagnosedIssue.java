package com.sdl.turnkeyapp.dto;

import lombok.Data;

@Data
public class DiagnosedIssue {

  private String icd;

  private String icdName;

  private String profName;

  private float accuracy;

  private float ranking;
}
