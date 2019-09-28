package by.arhor.psra.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Document("photos")
public class Photo extends Entity {
  
  private String name;
  private String description;
  private String path;
  private Set<String> tags;

  @DBRef(lazy = true)
  private List<Comment> comments;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Set<String> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public void addTag(String tag) {
    if (tag != null) {
      if (tags == null) {
        tags = new HashSet<>();
      }
      tags.add(tag);
    }
  }

  public void removeTag(String tag) {
    if ((tag != null) && (tags != null)) {
      tags.remove(tag);
    }
  }

  public List<Comment> getComments() {
    return Collections.unmodifiableList(comments);
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    if (comment != null) {
      if (comments == null) {
        comments = new ArrayList<>();
        comments.add(comment);
      } else if (!comments.contains(comment)) {
        comments.add(comment);
      }
    }
  }

  public void removeComment(Comment comment) {
    if ((comment != null) && (comments != null)) {
      comments.remove(comment);
    }
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
    Photo photo = (Photo) o;
    return Objects.equals(name, photo.name)
        && Objects.equals(description, photo.description)
        && Objects.equals(path, photo.path)
        && Objects.equals(tags, photo.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, path, tags);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Photo.class.getSimpleName() + "[", "]")
        .add("id=" + getId())
        .add("created='" + getDateTimeCreated() + "'")
        .add("updated='" + getDateTimeUpdated() + "'")
        .add("enabled=" + isEnabled())
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("path='" + path + "'")
        .add("tags=" + tags)
        .toString();
  }
}
