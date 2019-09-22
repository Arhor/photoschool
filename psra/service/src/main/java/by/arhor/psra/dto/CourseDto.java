package by.arhor.psra.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class CourseDto extends Dto {

  private String name;
  private String description;

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
    CourseDto courseDto = (CourseDto) o;
    return Objects.equals(name, courseDto.name)
        && Objects.equals(description, courseDto.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CourseDto.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .toString();
  }
}
