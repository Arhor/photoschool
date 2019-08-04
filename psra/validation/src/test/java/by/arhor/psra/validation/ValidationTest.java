package by.arhor.psra.validation;

import by.arhor.psra.config.ValidationTestConfig;
import by.arhor.psra.validation.exception.ConstraintViolationException;
import by.arhor.psra.validation.exception.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ValidationTestConfig.class})
@ActiveProfiles("test")
public class ValidationTest {

    @Autowired
    private TestComponent executor;

    @Test
    public void annotationTest() {
        TestEntity entity = new TestEntity();
        TestNestedEntity nestedEntity = new TestNestedEntity();

        entity.setName("too long name");
        entity.setAge(-1);
        entity.setPhone(12345.6789);
        entity.setBiography("");
        entity.setNestedCollection(Collections.singletonList(nestedEntity));
        entity.setNestedEntity(nestedEntity);
        entity.setPatternString("123");

        try {
            executor.process(entity);
            fail();
        } catch (ConstraintViolationException e) {
            Set<Integer> statusCodes = e.getStatusSet().stream().map(Status::getCode).collect(Collectors.toSet());

            assertThat(statusCodes, hasItem(Status.FAILED_DECIMAL_CHECK));
            assertThat(statusCodes, hasItem(Status.FAILED_EMPTY_CHECK));
            assertThat(statusCodes, hasItem(Status.FAILED_LENGTH_CHECK));
            assertThat(statusCodes, hasItem(Status.FAILED_POSITIVE_CHECK));
            assertThat(statusCodes, hasItem(Status.FAILED_NULL_CHECK));
            assertThat(statusCodes, hasItem(Status.FAILED_PATTERN_CHECK));
        }
    }

}
