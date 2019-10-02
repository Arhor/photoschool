package by.arhor.psra.localization;

public enum ErrorLabel implements Label {

  /* Not found codes */
  ENTITY_NOT_FOUND   ("error.not.found.entity"),
  COMMENT_NOT_FOUND  ("error.not.found.comment"),
  COURSE_NOT_FOUND   ("error.not.found.course"),
  GALLERY_NOT_FOUND  ("error.not.found.gallery"),
  PHOTO_NOT_FOUND    ("error.not.found.photo"),
  USER_NOT_FOUND     ("error.not.found.user"),

  /* Duplicate codes */
  ENTITY_DUPLICATE   ("error.duplicate.entity"),
  USER_DUPLICATE     ("error.duplicate.user");

  private final String value;

  ErrorLabel(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }

}
