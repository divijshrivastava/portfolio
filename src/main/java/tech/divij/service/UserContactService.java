package tech.divij.service;

import org.springframework.stereotype.Service;
import tech.divij.dao.UserContact;
import tech.divij.repository.UserContactRepository;

@Service
public class UserContactService {
  private final UserContactRepository userContactRepository;

  public UserContactService(UserContactRepository userContactRepository) {
    this.userContactRepository = userContactRepository;
  }

  public void saveUserContact(UserContact userContact) {
    userContactRepository.save(userContact);
  }
}
