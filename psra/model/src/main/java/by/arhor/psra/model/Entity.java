package by.arhor.psra.model;

import by.arhor.psra.traits.Auditable;
import by.arhor.psra.traits.Deletable;
import by.arhor.psra.traits.Identifiable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Entity
  implements Identifiable<String>
           , Auditable
           , Deletable {

  @Id
  private String id;

  @CreatedDate
  private LocalDateTime dateTimeCreated;

  @LastModifiedDate
  private LocalDateTime dateTimeUpdated;

  private boolean enabled;

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public LocalDateTime getDateTimeCreated() {
    return dateTimeCreated;
  }

  public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
    this.dateTimeCreated = dateTimeCreated;
  }

  @Override
  public LocalDateTime getDateTimeUpdated() {
    return dateTimeUpdated;
  }

  public void setDateTimeUpdated(LocalDateTime dateTimeUpdated) {
    this.dateTimeUpdated = dateTimeUpdated;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entity entity = (Entity) o;
    return enabled == entity.enabled
        && Objects.equals(id, entity.id)
        && Objects.equals(dateTimeCreated, entity.dateTimeCreated)
        && Objects.equals(dateTimeUpdated, entity.dateTimeUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateTimeCreated, dateTimeUpdated, enabled);
  }
}
