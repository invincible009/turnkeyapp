package com.sdl.turnkeyapp.services;

import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.DiagnosisResult;

public interface PatientService {
  DiagnosisResult patientDiagnosis(Symptom symptom);
}
