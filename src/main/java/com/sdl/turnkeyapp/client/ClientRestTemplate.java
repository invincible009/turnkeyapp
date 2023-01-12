package com.sdl.turnkeyapp.client;

import com.sdl.turnkeyapp.dto.Symptom;
import com.sdl.turnkeyapp.models.DiagnosisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientRestTemplate {

    private final RestTemplate template;

    @Autowired
    public ClientRestTemplate(RestTemplate template) {
        this.template = template;
    }

    public DiagnosisResult getDiagnosisResult(final Symptom symptom){
        HttpHeaders headers = new HttpHeaders();
        headers.set("headerName", "headerValue");
        HttpEntity<Symptom> request = new HttpEntity<>(symptom, headers);

        ResponseEntity<DiagnosisResult> response = template.exchange(
                "https://sandbox-healthservice.priaid.ch/diagnosis", HttpMethod.POST, request, DiagnosisResult.class);

        return response.getBody();

    }
}
