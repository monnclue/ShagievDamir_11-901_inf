package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

@Controller
public class ConfirmController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code) {
        User user = usersRepository.findByConfirmCode(code);
        user.setState(State.CONFIRMED);
        usersRepository.save(user);
        return "confirmed";
    }

}