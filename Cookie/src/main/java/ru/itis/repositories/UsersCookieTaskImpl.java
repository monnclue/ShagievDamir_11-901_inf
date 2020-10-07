package ru.itis.repositories;

import ru.itis.models.User;
import ru.itis.models.UserForCookieTask;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UsersCookieTaskImpl implements UsersCookieTaskRepository {

    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from userspass";
    //language=SQL
    private static final String SQL_FIND_USER_BY_LOGIN = "select * from userspass where login like ?";
    //language=SQL
    private static final String SQL_FIND_USER_BY_UUID = "select * from userspass where uuid like ?";


    public UsersCookieTaskImpl(Connection connection) {
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<UserForCookieTask> usersRowMapper = row -> UserForCookieTask.builder()
            .login(row.getString("login"))
            .password(row.getString("password"))
            .uuid(row.getString("uuid"))
            .build();

    @Override
    public List<UserForCookieTask> findByLogin(String login) {
        return jdbcTemplate.queryForList(SQL_FIND_USER_BY_LOGIN, usersRowMapper, login);
    }

    @Override
    public List<UserForCookieTask> findByUUID(String uuid) {
        return jdbcTemplate.queryForList(SQL_FIND_USER_BY_UUID, usersRowMapper, uuid);
    }


    @Override
    public Optional<UserForCookieTask> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserForCookieTask> findAll() {
        return null;
    }

    @Override
    public void save(UserForCookieTask entity) {

    }

    @Override
    public void update(UserForCookieTask entity) {

    }

    @Override
    public void delete(UserForCookieTask entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
