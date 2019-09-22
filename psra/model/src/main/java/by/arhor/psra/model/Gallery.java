package by.arhor.psra.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;


@Document("galleries")
public class Gallery extends Entity {

  private String name;
  private Access access;

  @DBRef(lazy = true)
  private List<Photo> photos;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Access getAccess() {
    return access;
  }

  public void setAccess(Access access) {
    this.access = access;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
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
    Gallery gallery = (Gallery) o;
    return Objects.equals(name, gallery.name)
        && access == gallery.access;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, access);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Gallery.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("access=" + access)
        .toString();
  }
}
