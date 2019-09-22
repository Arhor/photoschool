package by.arhor.psra.repository;

import by.arhor.psra.config.ModelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ ModelConfig.class })
@SpringBootApplication
public class RepositoryTestsRunner {
  public static void main(String[] args) {
    SpringApplication.run(RepositoryTestsRunner.class, args);
  }
}
