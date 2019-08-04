package by.arhor.psra.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("by.arhor.psra.validation")
@EnableAspectJAutoProxy
@Profile("test")
public class ValidationTestConfig {}
