package ru.itis;

import java.util.List;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Car> cars;

    public Driver(Long id, String firstName, String lastName, Integer age, List<Car> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cars = cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<Car> getCars() {
        return cars;
    }
}
