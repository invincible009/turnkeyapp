package com.sdl.turnkeyapp.dto;

import com.sdl.turnkeyapp.enums.Gender;
import java.util.List;
import lombok.Data;

@Data
public class Symptom {

  private List<Integer> selectedSymptoms;
  private Gender gender;
  private int yearOfBirth;

}
