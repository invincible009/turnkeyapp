package com.sdl.turnkeyapp.controller;

import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.services.PatientService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("app")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/new")
    public String patientRecord(Model model){
        Symptom symptom  = new Symptom();
        model.addAttribute("symptom", symptom);
        return "patientsymptom";
    }

    @PostMapping("/diagnosis")
    public String patientRecord(@ModelAttribute("symptom") @Valid Symptom symptom, Model  attributes){
        List<DiagnosedSpecialisation> diagnosis = patientService.patientDiagnosis(symptom);
        attributes.addAttribute("diagnosis",diagnosis);
        return "patientresult";
    }

    @GetMapping("/patient")
    public String patientDiagnosis(Model model){
        Symptom symptom = new Symptom();
        model.addAttribute("symptom", symptom);
        return "patientDiagnosis";
    }

    @PostMapping("/patient/result")
    public String patientDiagnosis(@ModelAttribute("symptom") @Valid Symptom symptom, Model model){
        List<HealthDiagnosis> result = patientService.patientDiagnosisResult(symptom);
        System.out.println(result);
        System.out.println(symptom);
        model.addAttribute("result", result);
        return "patientDiagnosisresult";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {

        List<HealthDiagnosis> healthDiagnosesData = patientService.getHealthDiagnosisData(query);

        List<HealthDiagnosis> filteredData = healthDiagnosesData.stream()
                .filter(d -> d.getIssue().getName().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("data", filteredData);
        return "dataTable";
    }

}
