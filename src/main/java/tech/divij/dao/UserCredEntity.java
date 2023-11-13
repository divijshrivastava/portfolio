package tech.divij.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER_CREDENTIALS")
public class UserCredEntity {

  @Id
  @Column(name = "U_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "USER_PASSWORD")
  private String password;
}
