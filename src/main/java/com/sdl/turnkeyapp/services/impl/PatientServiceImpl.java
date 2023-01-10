package com.sdl.turnkeyapp.services.impl;

import com.sdl.turnkeyapp.client.ClientRestTemplate;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.DiagnosisResult;
import com.sdl.turnkeyapp.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
  private final ClientRestTemplate client;

  @Autowired
  public PatientServiceImpl(ClientRestTemplate client) {
    this.client = client;
  }

  @Override
  public DiagnosisResult patientDiagnosis(Symptom symptom) {
    logger.info("Syptoms are {}", symptom);

        DiagnosisResult result = client.getDiagnosisResult(symptom);
        logger.info("The result is {}", result);
        return result;
  }

}
