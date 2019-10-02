package by.arhor.psra.dto;

import by.arhor.psra.CoreVersion;

import java.util.Objects;
import java.util.StringJoiner;

public final class UserDto extends Dto {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String username;
  private String password;
  private String email;
  private String name;
  private String surname;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    UserDto userDto = (UserDto) o;
    return Objects.equals(username, userDto.username)
        && Objects.equals(password, userDto.password)
        && Objects.equals(email, userDto.email)
        && Objects.equals(name, userDto.name)
        && Objects.equals(surname, userDto.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), username, password, email, name, surname);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UserDto.class.getSimpleName() + "[", "]")
        .add("username='" + username + "'")
        .add("password='" + password + "'")
        .add("email='" + email + "'")
        .add("name='" + name + "'")
        .add("surname='" + surname + "'")
        .toString();
  }
}
