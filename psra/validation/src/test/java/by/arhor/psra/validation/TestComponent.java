package by.arhor.psra.validation;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    public Object process(@Validate Object object) {
        return object;
    }

}
