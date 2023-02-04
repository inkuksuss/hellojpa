package jpql;

public class TestDto {

    private String value;
    private int age;

    public TestDto(String value, int age) {
        this.value = value;
        this.age = age;
    }

    public String getValue() {
        return value;
    }

    public int getAge() {
        return age;
    }
}
