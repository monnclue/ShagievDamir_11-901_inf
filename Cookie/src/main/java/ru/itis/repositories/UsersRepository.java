package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;

/**
 * 28.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
}
