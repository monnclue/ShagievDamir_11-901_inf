package ru.itis;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Car {
    private Long id;
    private String model;
    private String color;

    // TODO: на интерес
    private Driver driver;

    public Car(Long id, String model, String color, Driver driver) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.driver = driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Driver getDriver() {
        return driver;
    }
}