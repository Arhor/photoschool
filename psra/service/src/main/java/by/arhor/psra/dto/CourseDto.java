package by.arhor.psra.dto;

import by.arhor.psra.CoreVersion;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public final class CourseDto extends Dto {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String name;
  private String description;
  private short limit;
  private LocalDate startDate;
  private LocalDate endDate;

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

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
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
        && Objects.equals(description, courseDto.description)
        && Objects.equals(startDate, courseDto.startDate)
        && Objects.equals(endDate, courseDto.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, limit, startDate, endDate);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CourseDto.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("limit=" + limit)
        .add("startDate=" + startDate)
        .add("endDate=" + endDate)
        .toString();
  }
}
