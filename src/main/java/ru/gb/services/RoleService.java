package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.data.Role;
import ru.gb.repositories.IRoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }
}
