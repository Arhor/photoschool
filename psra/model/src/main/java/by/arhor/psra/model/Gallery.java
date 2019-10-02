package by.arhor.psra.model;

import by.arhor.psra.CoreVersion;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;


@Document("galleries")
public class Gallery extends Entity {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  private String name;
  private Access access = Access.PUBLIC;

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
    return Collections.unmodifiableList(photos);
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  public void addPhoto(Photo photo) {
    if (photo != null) {
      if (photos == null) {
        photos = new ArrayList<>();
        photos.add(photo);
      } else if (!photos.contains(photo)) {
        photos.add(photo);
      }
    }
  }

  public void removePhoto(Photo photo) {
    if ((photo != null) && (photos != null)) {
      photos.remove(photo);
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
    Gallery gallery = (Gallery) o;
    return access == gallery.access
        && Objects.equals(name, gallery.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, access);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Gallery.class.getSimpleName() + "[", "]")
        .add("id=" + getId())
        .add("created='" + getDateTimeCreated() + "'")
        .add("updated='" + getDateTimeUpdated() + "'")
        .add("enabled=" + isEnabled())
        .add("name='" + name + "'")
        .add("access=" + access)
        .toString();
  }
}
