package ru.itis.repositories;

import ru.itis.models.UserForCookieTask;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AuthorizationTemplate {
    private Connection connection;
    private UsersCookieTaskRepository usersRepository;

    public AuthorizationTemplate(Connection connection) {
        this.connection = connection;
        usersRepository = new UsersCookieTaskImpl(connection);
    }

    public Cookie getCookieForUser(String log, String pas) throws SQLException {
        UserForCookieTask user = null;
        Cookie cookie = null;
        user = usersRepository.findByLogin(log).get(0);
        System.out.println(user.getUuid());
        if (user.getPassword().equals(pas)) {
            if (user.getUuid() == null) {
                user.setUuid(UUID.randomUUID().toString());
                setUUID(user);
            }
            cookie = new Cookie("myCookie", user.getUuid());
        }


        return cookie;
    }

    public void deleteUUIDForUser(String uuid) throws SQLException {
        List<UserForCookieTask> users = usersRepository.findByUUID(uuid);
        UserForCookieTask user = users.get(0);
        user.setUuid(null);
        //setUUID(user);
    }

    private void setUUID(UserForCookieTask user) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(
                        "UPDATE userspass SET uuid = ? WHERE login = ?");
        statement.setString(1, user.getUuid());
        statement.setString(2, user.getLogin());
        statement.execute();
    }
}
