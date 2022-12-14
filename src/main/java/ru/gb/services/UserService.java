package ru.gb.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gb.data.Role;
import ru.gb.data.User;
import ru.gb.repositories.IRoleRepository;
import ru.gb.repositories.IUserRepository;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Page<User> getUserFilter(Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, 10, Sort.by("id").ascending()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleRepository.findByName(r.getName()).getId()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User editUser(User user) {
        user.setId(userRepository.findByUsername(user.getUsername()).get().getId());
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleRepository.findByName(r.getName()).getId()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

}
