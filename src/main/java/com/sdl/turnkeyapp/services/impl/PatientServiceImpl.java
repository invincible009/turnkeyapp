package com.sdl.turnkeyapp.services.impl;

import com.sdl.turnkeyapp.client.DiagnosisClient;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.DiagnosisResult;
import com.sdl.turnkeyapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  private final DiagnosisClient client;

  @Autowired
  public PatientServiceImpl(DiagnosisClient client) {
    this.client = client;
  }

  @Override
  public DiagnosisResult patientDiagnosis(Symptom symptom) {
    if(validHeaders()){
      return client.getDiagnosis(symptom);
    }
    addHeaders();
    return client.getDiagnosis(symptom);
  }

  private boolean validHeaders() {
    return true;
  }


  private void addHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("app_id", "your_app_id");
    headers.set("app_key", "your_app_key");
  }
}
