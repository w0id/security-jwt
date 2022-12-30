package ru.gb.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.data.Customer;
import ru.gb.data.Role;
import ru.gb.data.User;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.repositories.IUserRepository;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с id: %s не найден", id)));
    }

    public Page<User> getUserFilter(Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, 10, Sort.by("id").ascending()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void save(User user) {
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleService.getRole(r.getName()).getId()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void editUser(User user) {
        user.setId(userRepository.findByUsername(user.getUsername()).get().getId());
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleService.getRole(r.getName()).getId()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public Customer getCustomer(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + id + " не найден"))
                .getCustomer();
    }

}
