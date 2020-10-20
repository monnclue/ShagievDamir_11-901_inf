package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 28.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class SimpleJdbcTemplate {

    private Connection connection;

    public SimpleJdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... args) {
        System.out.println(args);
        try {
            ResultSet resultSet = null;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int position = 1;
            for (Object arg : args) {
                preparedStatement.setObject(position, arg);
                position++;
            }
            resultSet = preparedStatement.executeQuery();
            

            List<T> result = new ArrayList<>();

            if (resultSet == null) {
                throw new SQLException("Empty result");
            }

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
