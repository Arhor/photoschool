package by.arhor.psra.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
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
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("path='" + path + "'")
        .add("tags=" + tags)
        .toString();
  }
}
