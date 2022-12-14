package ru.gb.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.data.User;
import ru.gb.repositories.IUserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

}
