package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from account where email = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account";

    //language=SQL
    private static final String SQL_INSERT = "insert into account(email, first_name, last_name, hash_password) " +
            "values (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash_password"))
            .build();

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getHashPassword());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
