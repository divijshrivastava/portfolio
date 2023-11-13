package tech.divij.dao;

import static javax.persistence.FetchType.LAZY;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "AUTHOR")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {
  @Column(name = "AUTHOR_PHONE_COUNTRY_CODE")
  String country;
  @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL, fetch = LAZY)
  List<BlogEntity> blogList;
  @Id
  @Column(name = "AUTHOR_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int authorId;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUTHOR_USER_NAME", referencedColumnName = "USER_NAME")
  private UserEntity userName;

  @Column(name = "AUTHOR_FIRST_NAME")
  private String firstName;

  @Column(name = "AUTHOR_LAST_NAME")
  private String lastName;

  @Column(name = "AUTHOR_PHOTO_ID")
  private Integer photoId;

  @Column(name = "AUTHOR_EMAIL")
  private String email;

  @Column(name = "AUTHOR_PHONE")
  private String phone;
}
