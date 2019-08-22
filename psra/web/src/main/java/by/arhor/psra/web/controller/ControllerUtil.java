package by.arhor.psra.web.controller;

public class ControllerUtil {

  private ControllerUtil() {}

  static final String DEFAULT_PAGE_STRING = "1";
  static final String DEFAULT_SIZE_STRING = "10";

  private static final int DEFAULT_PAGE = Integer.parseInt(DEFAULT_PAGE_STRING);
  private static final int DEFAULT_SIZE = Integer.parseInt(DEFAULT_SIZE_STRING);

  static int normalizePage(int page) {
    return (page > 0) ? page : DEFAULT_PAGE;
  }
  static int normalizeSize(int size) {
    return (size > 0) ? size : DEFAULT_SIZE;
  }
}
