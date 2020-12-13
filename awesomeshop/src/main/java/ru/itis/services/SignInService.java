package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;

/**
 * 28.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface SignInService {
    UserDto signIn(UserForm userForm);
}
