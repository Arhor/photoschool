package by.arhor.psra.dto;

import java.util.Set;
import java.util.Objects;
import java.util.StringJoiner;

public class PhotoDto extends Dto {

  private String name;
  private String description;
  private String path;
  private Set<String> tags;

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
    PhotoDto photoDto = (PhotoDto) o;
    return Objects.equals(name, photoDto.name)
        && Objects.equals(description, photoDto.description)
        && Objects.equals(path, photoDto.path)
        && Objects.equals(tags, photoDto.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, path, tags);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PhotoDto.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("path='" + path + "'")
        .add("tags=" + tags)
        .toString();
  }
}
