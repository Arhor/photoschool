package by.arhor.psra.dto;

import by.arhor.psra.CoreVersion;

import java.util.Objects;
import java.util.StringJoiner;

public final class CourseDto extends Dto {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String name;
  private String description;
  private short limit;

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

  public short getLimit() {
    return limit;
  }

  public void setLimit(short limit) {
    this.limit = limit;
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
    return limit == courseDto.limit
        && Objects.equals(name, courseDto.name)
        && Objects.equals(description, courseDto.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, limit);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CourseDto.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("limit=" + limit)
        .toString();
  }
}
