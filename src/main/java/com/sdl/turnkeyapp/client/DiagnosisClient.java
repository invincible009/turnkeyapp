package com.sdl.turnkeyapp.client;

import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.DiagnosisResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "diagnosisClient", url = "https://sandbox-healthservice.priaid.ch")
public interface DiagnosisClient {

  @PostMapping(value = "/diagnosis")
  DiagnosisResult getDiagnosis(@RequestBody Symptom symptom);

//  @PutMapping(value = "/diagnosis/{id}")
//  void updateDiagnosis(@PathVariable String id, @RequestBody Validity validity);
//
//  @GetMapping(value = "/diagnosis")
//  List<DiagnosisResult> searchDiagnosis(@RequestParam String query);
}

