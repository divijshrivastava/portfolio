package tech.divij.mapper;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.dao.UserContactEntity;
import tech.divij.dto.UserContactDto;

@Component
@Slf4j
@Scope("singleton")
public class UserContactMapper {

  public UserContactDto mapEntityToDto(UserContactEntity userContactEntity) {

    return UserContactDto.builder().message(userContactEntity.getMessage())
      .email(userContactEntity.getEmail()).fullName(userContactEntity.getFullName())
      .time(userContactEntity.getCreatedAt().toString()).build();

  }

  public UserContactEntity mapDtoToEntity(UserContactDto userContactDto) {

    return UserContactEntity.builder().createdAt(LocalDateTime.now())
      .email(userContactDto.email).fullName(userContactDto.fullName)
      .message(userContactDto.message).build();
  }
}
