package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.Role;
import ru.gb.market.core.repositories.IRoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }
}
