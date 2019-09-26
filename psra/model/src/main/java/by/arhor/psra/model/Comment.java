package by.arhor.psra.model;

import by.arhor.psra.traits.Likable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.StringJoiner;

@Document("comments")
public class Comment extends Entity implements Likable {

  private String text;
  private int likes;
  
  @DBRef
  private User user;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int getLikes() {
    return likes;
  }

  @Override
  public void setLikes(int likes) {
    this.likes = likes;
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
        && Objects.equals(user, comment.user)
        && likes == comment.likes;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text, user, likes);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
        .add("content='" + text + "'")
        .add("user=" + user)
        .add("likes=" + likes)
        .toString();
  }
}
