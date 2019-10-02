package by.arhor.psra.model;

import by.arhor.psra.CoreVersion;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Document("courses")
public class Course extends Entity {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
  private static final short UNLIMITED = -1;

  private String name;
  private String description;
  private short limit = UNLIMITED;
  private LocalDate startDate;
  private LocalDate endDate;

  @DBRef(lazy = true)
  private User teacher;

  @DBRef(lazy = true)
  private List<User> learners;

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
    this.limit = (limit > 0) ? limit : UNLIMITED;
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

  public User getTeacher() {
    return teacher;
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public List<User> getLearners() {
    return Collections.unmodifiableList(learners);
  }

  public void setLearners(List<User> learners) {
    this.learners = learners;
  }

  public void addLearner(User user) {
    if (user != null) {
      if (learners == null) {
        learners = new ArrayList<>();
        learners.add(user);
      } else if (!learners.contains(user)) {
        learners.add(user);
      }
    }
  }

  public void removeLearner(User user) {
    if ((user != null) && (learners != null)) {
      learners.remove(user);
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
    Course course = (Course) o;
    return limit == course.limit
        && Objects.equals(name, course.name)
        && Objects.equals(description, course.description)
        && Objects.equals(startDate, course.startDate)
        && Objects.equals(endDate, course.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, limit, startDate, endDate);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
        .add("id=" + getId())
        .add("created='" + getDateTimeCreated() + "'")
        .add("updated='" + getDateTimeUpdated() + "'")
        .add("enabled=" + isEnabled())
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("limit=" + ((limit == UNLIMITED) ? "UNLIMITED" : limit))
        .add("startDate=" + startDate)
        .add("endDate=" + endDate)
        .toString();
  }
}
