package ru.itis.repositories;

import ru.itis.models.User;

import java.util.Optional;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
