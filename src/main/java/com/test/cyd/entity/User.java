package com.test.cyd.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = User.TABLENAME)
public class User {
  public static final String TABLENAME = "myuser";

  public static final String USERID = "userId";
  public static final String FIRSTNAME = "firstname";
  public static final String LASTNAME = "lastname";
  public static final String CREATED_DATE = "created_date";

  @Id
  @Column(name = USERID)
  private String userId;

  @Column(name = FIRSTNAME, nullable = false)
  private String firstname;

  @Column(name = LASTNAME, nullable = false)
  private String lastname;

  @Column(name = CREATED_DATE, nullable = false)
  private Date createdDate;

  public User() {
  }

  public User(String userId, String firstname, String lastname) {
    this.userId = userId;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return userId.equals(user.userId);
  }

  @Override
  public int hashCode() {
    return 42 * userId.hashCode();
  }
}
