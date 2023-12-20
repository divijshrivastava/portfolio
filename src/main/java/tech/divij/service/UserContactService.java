package tech.divij.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.divij.dto.UserContactDto;
import tech.divij.entity.UserContactEntity;
import tech.divij.mapper.UserContactMapper;
import tech.divij.repository.UserContactRepository;

@Service
public class UserContactService {

  private final UserContactRepository userContactRepository;

  private final UserContactMapper userContactMapper;

  private final UserAuthenticationService userAuthenticationService;

  public UserContactService(UserContactRepository userContactRepository,
    UserContactMapper userContactMapper,
    UserAuthenticationService userAuthenticationService) {
    this.userContactRepository = userContactRepository;
    this.userContactMapper = userContactMapper;
    this.userAuthenticationService = userAuthenticationService;
  }

  public void saveUserContact(UserContactDto userContactDto) {
    UserContactEntity userContactEntity =
      userContactMapper.mapDtoToEntity(userContactDto);
    userContactRepository.save(userContactEntity);
  }

  public Page<UserContactDto> fetchContacts(Pageable pageable) {
    Page<UserContactEntity> userContactEntities =
      userContactRepository.findAll(pageable);
    List<UserContactDto> userContactDtos =
      userContactEntities.getContent()
        .stream()
        .map(userContactMapper::mapEntityToDto)
        .collect(Collectors.toList());
    return new PageImpl<>(userContactDtos, pageable,
      userContactEntities.getTotalElements());
  }

  public Long getTotalContacts() {
    return userAuthenticationService.isAdmin() ? userContactRepository.count() : 0;
  }
}
