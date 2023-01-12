package com.sdl.turnkeyapp.dto;

import java.util.List;
import lombok.Data;

@Data
public class HealthDiagnosis {

  private DiagnosedIssue Issue;

  private List<DiagnosedSpecialisation> Specialisation;
}
