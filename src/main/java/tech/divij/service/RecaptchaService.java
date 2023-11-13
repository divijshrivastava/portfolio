package tech.divij.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {

  public static final String RESPONSE_PARAMETER = "&response=";
  public static final String SUCCESS = "success";

  @Value("${secret.key}")
  private String secret;

  @Value("${recaptcha.api.url}")
  private String recaptchaApiUrl;

  public Boolean isCaptchaValid(String captchaResponse) {
    String url = recaptchaApiUrl + secret + RESPONSE_PARAMETER + captchaResponse;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(url, String.class);
    JsonObject jsonObject =
        JsonParser.parseString(Objects.requireNonNull(result)).getAsJsonObject();
    return jsonObject.get(SUCCESS).getAsBoolean();
  }
}
