package com.sdl.turnkeyapp.services.impl;


import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.services.ApiMedicRestService;
import com.sdl.turnkeyapp.services.PatientService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  private final ApiMedicRestService medicRestService;

  @Autowired
  public PatientServiceImpl( ApiMedicRestService medicRestService) {
    this.medicRestService = medicRestService;
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
}
