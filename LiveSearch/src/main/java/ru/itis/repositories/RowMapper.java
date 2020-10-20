package ru.itis.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 28.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
