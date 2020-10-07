package ru.itis.repositories;

import ru.itis.models.UserForCookieTask;

import java.util.List;

public interface UsersCookieTaskRepository extends CrudRepository<UserForCookieTask> {
    List<UserForCookieTask> findByLogin(String login);
    List<UserForCookieTask> findByUUID(String uuid);
}
