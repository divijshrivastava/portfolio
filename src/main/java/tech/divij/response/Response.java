package tech.divij.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder(toBuilder = true)
public class Response<Message> {

  private Message message;
  private String responseCode;
  private String responseMessage;
}
