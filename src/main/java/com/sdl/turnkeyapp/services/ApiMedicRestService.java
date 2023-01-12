package com.sdl.turnkeyapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdl.turnkeyapp.dto.AccessToken;
import com.sdl.turnkeyapp.dto.DiagnosedSpecialisation;
import com.sdl.turnkeyapp.dto.HealthDiagnosis;
import com.sdl.turnkeyapp.dto.HealthIssueInfo;
import com.sdl.turnkeyapp.dto.HealthItem;
import com.sdl.turnkeyapp.dto.HealthSymptomSelector;
import com.sdl.turnkeyapp.enums.Gender;
import com.sdl.turnkeyapp.enums.SelectorStatus;
import java.util.List;

public interface ApiMedicRestService {

  AccessToken login();
  AccessToken getLoginToken(String username, String password, String url) throws Exception;

  <T> T makeHttpCall(String action, TypeReference<?> valueTypeRef) throws Exception;
  void handleRequiredArguments(String username, String password, String authServiceUrl, String language, String healthServiceUrl);
  List<HealthItem> loadSymptoms() throws Exception;
  List<HealthItem> loadIssues() throws Exception;
  HealthIssueInfo loadIssueInfo(int issueId) throws Exception;
  List<HealthDiagnosis> loadDiagnosis(List<Integer> selectedSymptoms, Gender gender, int yearOfBirth) throws Exception;
  List<DiagnosedSpecialisation> loadSpecialisations(List<Integer> selectedSymptoms, Gender gender, int yearOfBirth) throws Exception;
  List<HealthItem> loadBodyLocations() throws Exception;
  List<HealthItem> loadBodySubLocations(int bodyLocationId) throws Exception;
  List<HealthSymptomSelector> loadSublocationSymptoms(int locationId, SelectorStatus selectedSelectorStatus) throws Exception;
  List<HealthItem> loadProposedSymptoms(List<Integer> selectedSymptoms, Gender gender, Integer yearOfBirth) throws Exception;
  String loadRedFlag(int symptomId) throws Exception;
}
