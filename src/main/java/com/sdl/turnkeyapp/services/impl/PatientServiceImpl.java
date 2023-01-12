package com.sdl.turnkeyapp.services.impl;


import com.sdl.turnkeyapp.services.ApiMedicRestService;
import com.sdl.turnkeyapp.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  private final ApiMedicRestService medicRestService;

  @Autowired
  public PatientServiceImpl( ApiMedicRestService medicRestService) {
    this.medicRestService = medicRestService;
  }



}
