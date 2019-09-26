package by.arhor.psra.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Document("users")
public class User extends Entity {

  @Indexed(unique = true)
  private String username;
  private String password;
  private Role role = Role.GUEST;

  @Indexed(unique = true)
  private String email;
  private String name;
  private String surname;

  @DBRef(lazy = true)
  private List<Gallery> galleries;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public List<Gallery> getGalleries() {
    return galleries;
  }

  public void setGalleries(List<Gallery> galleries) {
    this.galleries = galleries;
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
    User user = (User) o;
    return Objects.equals(username, user.username)
        && Objects.equals(password, user.password)
        && Objects.equals(email, user.email)
        && Objects.equals(name, user.name)
        && Objects.equals(surname, user.surname)
        && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), username, password, role, email, name, surname);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("username='" + username + "'")
        .add("password='" + password + "'")
        .add("role=" + role)
        .add("email='" + email + "'")
        .add("name='" + name + "'")
        .add("surname='" + surname + "'")
        .toString();
  }
}
