package by.arhor.psra.dto;

import by.arhor.psra.CoreVersion;

import java.util.Objects;
import java.util.StringJoiner;

public final class GalleryDto extends Dto {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    GalleryDto that = (GalleryDto) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GalleryDto.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .toString();
  }
}
