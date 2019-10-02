package by.arhor.psra.dto;

import by.arhor.psra.CoreVersion;

import java.util.Objects;
import java.util.StringJoiner;

public final class CommentDto extends Dto {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String text;
  private UserDto user;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
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
    CommentDto that = (CommentDto) o;
    return Objects.equals(text, that.text)
        && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text, user);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CommentDto.class.getSimpleName() + "[", "]")
        .add("text='" + text + "'")
        .add("user=" + user)
        .toString();
  }
}
