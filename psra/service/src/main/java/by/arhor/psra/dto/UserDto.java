package by.arhor.psra.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class UserDto extends Dto {

  private String username;

  private String password;

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
        && Objects.equals(password, userDto.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), username, password);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UserDto.class.getSimpleName() + "[", "]")
        .add("username='" + username + "'")
        .add("password='" + password + "'")
        .toString();
  }
}
