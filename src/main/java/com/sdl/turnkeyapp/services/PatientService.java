package com.sdl.turnkeyapp.services;

import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.ValidDiagnosis;

import java.util.List;

public interface PatientService {

    List<HealthItem> allSymptoms();

    List<DiagnosedSpecialisation> patientDiagnosis(Symptom symptom);

    List<HealthDiagnosis> patientDiagnosisResult(Symptom symptom);

    List<HealthDiagnosis> getHealthDiagnosisData(String query);

    void persistUserDiagnosisData(List<HealthDiagnosis> lst);

    void validDiagnosis(List<HealthDiagnosis> lst);
}
