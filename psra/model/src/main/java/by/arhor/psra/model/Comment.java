package by.arhor.psra.model;

import by.arhor.psra.CoreVersion;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.StringJoiner;

@Document("comments")
public class Comment extends Entity {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String text;

  @DBRef
  private User user;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
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
    Comment comment = (Comment) o;
    return Objects.equals(text, comment.text)
        && Objects.equals(user, comment.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text, user);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
        .add("id=" + getId())
        .add("created='" + getDateTimeCreated() + "'")
        .add("updated='" + getDateTimeUpdated() + "'")
        .add("enabled=" + isEnabled())
        .add("content='" + text + "'")
        .add("user=" + user)
        .toString();
  }
}
