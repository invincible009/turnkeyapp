package com.sdl.turnkeyapp.controller;

import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.services.PatientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping("/status")
    public String getAppStatus(Model model){
        List<HealthItem> healthItems = patientService.allSymptoms();
        model.addAttribute("items", healthItems);
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
