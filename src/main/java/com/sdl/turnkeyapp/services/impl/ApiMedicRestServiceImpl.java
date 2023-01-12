package com.sdl.turnkeyapp.services.impl;
import Decoder.BASE64Encoder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdl.turnkeyapp.dto.*;
import com.sdl.turnkeyapp.enums.Gender;
import com.sdl.turnkeyapp.enums.SelectorStatus;
import com.sdl.turnkeyapp.services.ApiMedicRestService;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ApiMedicRestServiceImpl implements ApiMedicRestService {

  public Logger logger = LoggerFactory.getLogger(ApiMedicRestServiceImpl.class);

  private AccessToken accessToken;
  @Value("${turnkey.username}")
  private String username;

  @Value("${turnkey.password}")
  private String password;

  @Value("${turnkey.authServiceUrl}")
  private String authServiceUrl;

  @Value("${turnkey.language}")
  private String language;

  @Value("${turnkey.healthServiceUrl}")
  private String healthServiceUrl;


  private CloseableHttpClient httpclient;

  @Autowired
  public ApiMedicRestServiceImpl() {
    this.httpclient = HttpClients.createDefault();
  }


  @Override
  public AccessToken login() {
    return null;
  }

  @Override
  public AccessToken getLoginToken(String username, String password, String url) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(
        password.getBytes(),
        "HmacMD5");

    String computedHashString = "";
    try {
      Mac mac = Mac.getInstance("HmacMD5");
      mac.init(keySpec);
      byte[] result = mac.doFinal(url.getBytes());

      BASE64Encoder encoder = new BASE64Encoder();
      computedHashString = encoder.encode(result);

    } catch (NoSuchAlgorithmException e) {
      throw new Exception("Can not create token (NoSuchAlgorithmException)" + e.getMessage());
    } catch (InvalidKeyException e) {

      throw new Exception("Can not create token (InvalidKeyException)" + e.getMessage());
    }

    HttpPost httpPost = new HttpPost(url);
    httpPost.setHeader("Authorization", "Bearer " + username + ":" + computedHashString);

    try {
      CloseableHttpResponse response = httpclient.execute(httpPost);

      ObjectMapper objectMapper = new ObjectMapper();
      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new Exception(
            objectMapper.readValue(response.getEntity().getContent(), String.class));
      }
      AccessToken token = objectMapper.readValue(response.getEntity().getContent(),
          AccessToken.class);
      accessToken = token;
      return token;

    } catch (ClientProtocolException e) {

      throw new Exception("Can not create token (ClientProtocolException)" + e.getMessage());
    } catch (IOException e) {
      throw new Exception("Can not create token (IOException)" + e.getMessage());
    }
  }

  @Override
  public <T> T makeHttpCall(String action, TypeReference<?> valueTypeRef) throws Exception {
    AccessToken token = validToken(accessToken);
    String extraArgs = "token=" + token.getToken() + "&format=json&language=" + this.language;
    String url = new StringBuilder(this.healthServiceUrl).append("/").append(action)
        .append(action.contains("?") ? "&" : "?").append(extraArgs).toString();

    HttpGet httpGet = new HttpGet(url);
    CloseableHttpResponse response;
    try {
      response = httpclient.execute(httpGet);
      ObjectMapper objectMapper = new ObjectMapper();
      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new Exception(
            objectMapper.readValue(response.getEntity().getContent(), String.class));
      }
      return (T) objectMapper.readValue(response.getEntity().getContent(), valueTypeRef);
    } catch (IOException e) {
      throw new Exception("Fail communication with web-service" + e.getMessage());
    }
  }

  private AccessToken validToken(AccessToken aToken) {
    if (aToken == null) {
      return refreshToken();
    }
    return accessToken;
  }

  private AccessToken refreshToken() {
    try {
      return getLoginToken(username, password, authServiceUrl);
    } catch (Exception e) {
      throw new RuntimeException("Throw Exception" + e.getMessage());
    }
  }


  @Override
  public void handleRequiredArguments(String username, String password, String authServiceUrl,
      String language, String healthServiceUrl) {

  }

  @Override
  public List<HealthItem> loadSymptoms() throws Exception {
    return makeHttpCall("symptoms", new TypeReference<List<HealthItem>>() {
    });
  }

  @Override
  public List<HealthItem> loadIssues() throws Exception {
    return makeHttpCall("issues", new TypeReference<List<HealthItem>>() {
    });
  }

  @Override
  public HealthIssueInfo loadIssueInfo(int issueId) throws Exception {
    String action = "issues/" + issueId + "/info";
    return makeHttpCall(action, new TypeReference<HealthIssueInfo>() {
    });
  }

  @Override
  public List<HealthDiagnosis> loadDiagnosis(Symptom symptom) throws Exception {
    if (symptom.getSelectedSymptoms() == null || symptom.getSelectedSymptoms().size() == 0) {
      throw new IllegalArgumentException("selectedSymptoms  Can not be null or empty");
    }

    String serializedSymptoms = new ObjectMapper().writeValueAsString(symptom.getSelectedSymptoms());
    String action = "diagnosis?symptoms=" + serializedSymptoms + "&gender=" + symptom.getGender().toString()
        + "&year_of_birth=" + symptom.getYearOfBirth();
    return makeHttpCall(action, new TypeReference<List<HealthDiagnosis>>() {
    });
  }

  @Override
  public List<DiagnosedSpecialisation> loadSpecialisations(Symptom symptom) throws Exception {
    if (symptom.getSelectedSymptoms() == null || symptom.getSelectedSymptoms().size() == 0) {
      throw new IllegalArgumentException("selectedSymptoms  Can not be null or empty");
    }

    String serializedSymptoms = new ObjectMapper().writeValueAsString(symptom.getSelectedSymptoms());
    String action =
        "diagnosis/specialisations?symptoms=" + serializedSymptoms + "&gender=" + symptom.getGender().toString()
            + "&year_of_birth=" + symptom.getYearOfBirth();
    return makeHttpCall(action, new TypeReference<List<DiagnosedSpecialisation>>() {
    });
  }

  @Override
  public List<HealthItem> loadBodyLocations() throws Exception {
    return makeHttpCall("body/locations", new TypeReference<List<HealthItem>>() {
    });
  }

  @Override
  public List<HealthItem> loadBodySubLocations(int bodyLocationId) throws Exception {
    String action = "body/locations/" + bodyLocationId;
    return makeHttpCall(action, new TypeReference<List<HealthItem>>() {
    });
  }

  @Override
  public List<HealthSymptomSelector> loadSublocationSymptoms(int locationId,
      SelectorStatus selectedSelectorStatus) throws Exception {
    String action = "symptoms/" + locationId + "/" + selectedSelectorStatus.toString();
    return this.makeHttpCall(action, new TypeReference<List<HealthSymptomSelector>>() {
    });
  }

  @Override
  public List<HealthItem> loadProposedSymptoms(List<Integer> selectedSymptoms, Gender gender,
      Integer yearOfBirth) throws Exception {
    if (selectedSymptoms == null || selectedSymptoms.size() == 0) {
      throw new IllegalArgumentException("selectedSymptoms  Can not be null or empty");
    }

    String serializedSymptoms = new ObjectMapper().writeValueAsString(selectedSymptoms);
    String action =
        "symptoms/proposed?symptoms=" + serializedSymptoms + "&gender=" + gender.toString()
            + "&year_of_birth=" + yearOfBirth;

    return this.makeHttpCall(action, new TypeReference<List<HealthItem>>() {
    });
  }

  @Override
  public String loadRedFlag(int symptomId) throws Exception {
    String action = "redflag?symptomId=" + symptomId;
    return this.makeHttpCall(action,  new TypeReference<String>(){});
  }

  public static Object deserialize(InputStream inputStream)
      throws IOException, ClassNotFoundException {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
      return objectInputStream.readObject();
    }
  }

}
