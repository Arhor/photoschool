package by.arhor.psra.dto;

import by.arhor.psra.traits.Auditable;
import by.arhor.psra.traits.Deletable;
import by.arhor.psra.traits.Identifiable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Dto
    implements Identifiable<String>
             , Auditable
             , Deletable
             , Serializable {

  // TODO: do I really need such a lot of info in dto?
  private String id;
  private LocalDateTime dateTimeCreated;
  private LocalDateTime dateTimeUpdated;
  private boolean enabled;

  public Dto() {
    this.enabled = true;
  }

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
    Dto dto = (Dto) o;
    return enabled == dto.enabled
        && Objects.equals(id, dto.id)
        && Objects.equals(dateTimeCreated, dto.dateTimeCreated)
        && Objects.equals(dateTimeUpdated, dto.dateTimeUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateTimeCreated, dateTimeUpdated, enabled);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Dto.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("dateTimeCreated=" + dateTimeCreated)
        .add("dateTimeUpdated=" + dateTimeUpdated)
        .add("enabled=" + enabled)
        .toString();
  }
}
