package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByConfirmCode(String confirmCode);
}