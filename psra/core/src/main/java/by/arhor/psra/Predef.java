package by.arhor.psra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Predef {

  private Predef() {}

  @SafeVarargs
  public static <T> Set<T> setOf(T ...args) {
    final Set<T> set = new HashSet<>(args.length);
    Collections.addAll(set, args);
    return set;
  }

  @SafeVarargs
  public static <T> List<T> listOf(T ...args) {
    final List<T> list = new ArrayList<>(args.length);
    Collections.addAll(list, args);
    return list;
  }

  public static int[] range(int start, int end) {
    if (start <= end) {
      final int[] range = new int[(end + 1) - start];
      for (int i = 0; i < range.length; i++) {
        range[i] = start++;
      }
      return range;
    } else {
      final int[] range = new int[(start + 1) - end];
      for (int i = 0; i < range.length; i++) {
        range[i] = end++;
      }
      return range;
    }
  }

}
