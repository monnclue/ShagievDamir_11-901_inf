package ru.itis.services;

import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;


public class CheckingServiceImpl implements CheckingService {
    private UsersRepository usersRepository;

    public CheckingServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String incorrectInput(UserForm user) {

        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Пользователь с таким email уже существует.";
        }
        return null;
    }
}
