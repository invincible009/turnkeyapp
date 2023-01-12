package com.sdl.turnkeyapp.services.impl;


import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.repository.IssueRepo;
import com.sdl.turnkeyapp.repository.SpecializationItemRepo;
import com.sdl.turnkeyapp.services.ApiMedicRestService;
import com.sdl.turnkeyapp.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  private final ApiMedicRestService medicRestService;
  private final IssueRepo issueRepo;
  private final SpecializationItemRepo specializationItemRepo;

  @Autowired
  public PatientServiceImpl(ApiMedicRestService medicRestService, IssueRepo issueRepo, SpecializationItemRepo specializationItemRepo) {
    this.medicRestService = medicRestService;
    this.issueRepo = issueRepo;
    this.specializationItemRepo = specializationItemRepo;
  }


  @Override
  public List<HealthItem> allSymptoms() {
    return null;
  }

  @Override
  public List<DiagnosedSpecialisation> patientDiagnosis(Symptom symptom) {
    try {
      return medicRestService.loadSpecialisations(symptom);
    } catch (Exception e) {
      throw new RuntimeException("Fail to load patient diagnosis "+ e.getMessage());
    }
  }

  @Override
  public List<HealthDiagnosis> patientDiagnosisResult(Symptom symptom) {
    try {
      return medicRestService.loadDiagnosis(symptom);
    } catch (Exception e) {
      throw new RuntimeException("Fail to load patient diagnosis "+ e.getMessage());
    }

  }

  @Override
  public List<HealthDiagnosis> getHealthDiagnosisData(String query) {
    //Todo Look for the keyword from the two repos and return where the query is found.
    return null;
  }

  @Override
  public void persistUserDiagnosisData(List<HealthDiagnosis> lst) {
    //todo  loop through the list, get the issues and save in the issues repo
    //todo  loop through the list, get the specialisation repo and save in the specialisation repo
  }

  @Override
  public void validDiagnosis(List<HealthDiagnosis> lst) {
    persistUserDiagnosisData(lst);
  }
}
