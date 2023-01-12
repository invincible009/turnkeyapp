package com.sdl.turnkeyapp.services;

import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.Symptom;

import java.util.List;

public interface PatientService {

 List<HealthItem> allSymptoms();
 List<DiagnosedSpecialisation> patientDiagnosis(Symptom symptom);
 List<HealthDiagnosis> patientDiagnosisResult(Symptom symptom);
}
