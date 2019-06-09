package by.arhor.util;

import org.junit.jupiter.api.Test;

public class LazyTest {

  @Test
  void test() {
    var val = Lazy.eval(() -> 1)
                  .filter(x -> x % 2 == 0)
                  .map(String::valueOf)
                  .map(s -> s.charAt(0))
                  .map(c -> c.charValue());

    System.out.println(val.get());
  }

}
