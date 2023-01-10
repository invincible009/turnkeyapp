package com.sdl.turnkeyapp.controller;

import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.exception.BadRequestException;
import com.sdl.turnkeyapp.models.DiagnosisResult;
import com.sdl.turnkeyapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("app")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/status")
    public String getAppStatus(){
        return "patientsymptom";
    }

    @GetMapping("/diagnosis")
    public DiagnosisResult getDiagnosis(@RequestBody Symptom symptom) {
        try{
            return patientService.patientDiagnosis(symptom);
        }catch (BadRequestException e){
            throw new BadRequestException("Bad request "+ e.getMessage());
        }
    }
}
