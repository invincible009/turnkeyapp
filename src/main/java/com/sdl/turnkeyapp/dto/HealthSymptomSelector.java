package com.sdl.turnkeyapp.dto;

import java.util.List;
import lombok.Data;

@Data
public class HealthSymptomSelector extends HealthItem{

  private String profName;

  private Boolean hasRedFlag;

  private List<Integer> healthSymptomLocationIDs;

  private List<String> synonyms;
}
