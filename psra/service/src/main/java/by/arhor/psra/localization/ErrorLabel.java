package by.arhor.psra.localization;

public enum ErrorLabel implements Label {

  COMMENT_NOT_FOUND  ("error.not.found.comment"),
  COURSE_NOT_FOUND   ("error.not.found.course"),
  GALLERY_NOT_FOUND  ("error.not.found.gallery"),
  PHOTO_NOT_FOUND    ("error.not.found.photo"),
  USER_NOT_FOUND     ("error.not.found.user");

  private final String value;

  ErrorLabel(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }
}
