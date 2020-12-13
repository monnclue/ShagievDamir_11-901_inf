package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(Long id);

    Optional<T> findById(Long id);
    List<T> findAll();
}
