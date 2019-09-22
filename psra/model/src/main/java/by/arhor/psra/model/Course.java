package by.arhor.psra.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Document("courses")
public class Course extends Entity {

  @Indexed(unique = true)
  private String name;
  
  private String description;

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

  public User getTeacher() {
    return teacher;
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public List<User> getLearners() {
    return learners;
  }

  public void setLearners(List<User> learners) {
    this.learners = learners;
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
    return Objects.equals(name, course.name)
        && Objects.equals(description, course.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .toString();
  }
}
