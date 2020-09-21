package ru.itis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
// DATA ACCESS OBJECT
public class DriversDao {

    private Connection connection;
    private Driver driver;

    public DriversDao(Connection connection) {
        this.connection = connection;
    }



    public Optional<Driver> findById(Long id) throws SQLException {
        Statement statementDriver = connection.createStatement();
        ResultSet drivers = statementDriver.executeQuery(
                String.format("select * from driver where id = %d", id));

        if(!drivers.next()) {
            return  Optional.empty();
        }

        Statement statementCar = connection.createStatement();
        ResultSet cars = statementCar.executeQuery(
                String.format("select * from car where driver_id = %d",
                        id));
        ArrayList<Car> carsList = new ArrayList<>();

        while (cars.next()) {
            carsList.add(new Car(cars.getLong("id"),
                    cars.getString("model"),
                    cars.getString("color"),
                    null
                    ));
        }

        Driver driver = new Driver(
                drivers.getLong("id"),
                drivers.getString("firstName"),
                drivers.getString("lastName"),
                drivers.getInt("age"),
                null
        );

        driver.setCars(carsList);
        for (Car car : carsList) {
            car.setDriver(driver);
        }

        return Optional.of(driver);
    }
}
