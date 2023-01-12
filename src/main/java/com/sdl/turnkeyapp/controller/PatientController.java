package com.sdl.turnkeyapp.controller;

import com.sdl.turnkeyapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("app")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/login")
    public String loginUser() throws Exception {
        patientService.login();
        return "patientresult";
    }

    @GetMapping("/status")
    public String getAppStatus()throws Exception{
        patientService.login();
        return "patientsymptom";
    }

//    @GetMapping("/diagnosis")
//    public DiagnosisResult getDiagnosis(@RequestBody Symptom symptom) {
//        try{
//            return patientService.patientDiagnosis(symptom);
//        }catch (BadRequestException e){
//            throw new BadRequestException("Bad request "+ e.getMessage());
//        }
//    }
}
