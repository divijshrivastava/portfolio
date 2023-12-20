package tech.divij.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.divij.entity.AuthorEntity;
import tech.divij.repository.AuthorRepository;

@Service
@Slf4j
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public AuthorEntity fetchAuthorIdByUserName(String userName) {

    return authorRepository.findByUserName(userName);
  }
}
