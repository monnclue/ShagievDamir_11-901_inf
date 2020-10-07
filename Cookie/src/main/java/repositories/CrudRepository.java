package repositories;

import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);

}
