package tech.divij.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "AUTHORITIES")
public class Authority {

  @Id
  @Column(name = "AUTHORITY_NAME")
  private String authorityName;

  public Authority() {}

  public String getAuthorityName() {
    return authorityName;
  }

  public void setAuthorityName(String authorityName) {
    this.authorityName = authorityName;
  }
}
