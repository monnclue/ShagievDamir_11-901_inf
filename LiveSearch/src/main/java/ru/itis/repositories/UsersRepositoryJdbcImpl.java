package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 28.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from drivers";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_AGE = "select * from drivers where age = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_NAME = "select * from drivers where firstname like ?";

    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> usersRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstname"))
            .lastName(row.getString("lastname"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
       return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_AGE, usersRowMapper, age);
    }

    @Override
    public List<User> findAllByName(String name) {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_NAME, usersRowMapper, name + "%");
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, usersRowMapper);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
