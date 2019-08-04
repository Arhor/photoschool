package by.arhor.psra.validation;

import by.arhor.psra.validation.constraint.*;

import java.util.List;

public class TestEntity {

    @Size(min = 5, max = 10)
    private String name;

    @Positive
    private Integer age;

    @Decimal(precision = 5, scale = 2)
    private Double phone;

    @NotEmpty
    private String biography;

    @Pattern(value = "[a-z]{4,10}")
    private String patternString;

    @Validate
    private List<TestNestedEntity> nestedCollection;

    @Validate
    private TestNestedEntity nestedEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPhone() {
        return phone;
    }

    public void setPhone(Double phone) {
        this.phone = phone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patternString) {
        this.patternString = patternString;
    }

    public List<TestNestedEntity> getNestedCollection() {
        return nestedCollection;
    }

    public void setNestedCollection(List<TestNestedEntity> nestedCollection) {
        this.nestedCollection = nestedCollection;
    }

    public TestNestedEntity getNestedEntity() {
        return nestedEntity;
    }

    public void setNestedEntity(TestNestedEntity nestedEntity) {
        this.nestedEntity = nestedEntity;
    }
}
