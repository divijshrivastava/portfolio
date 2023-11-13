package tech.divij.dao;

import javax.persistence.*;

@Entity
@Table(name = "USER_CONTACT")
public class UserContact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "FULL_NAME")
  private String fullName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE_NUMBER")
  private String phone;

  @Column(name = "MESSAGE")
  private String message;

  // Constructor, getters, setters, and other methods

  public UserContact() {}

  public UserContact(Long id, String fullName, String email, String phone, String message) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
