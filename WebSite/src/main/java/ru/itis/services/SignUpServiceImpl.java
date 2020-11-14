package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class SignUpServiceImpl implements SignUpService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm form) {
        // passwordEncoder.matches("qwerty007", "$2a$10$YT4skQkJ84utGSRs5uiIo.5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu");
        User user = User.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();

        usersRepository.save(user);
    }
}
