package com.example.username.service.impl;

import com.example.username.dto.role.RoleDto;
import com.example.username.model.Role;
import com.example.username.repo.RoleRepo;
import com.example.username.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public RoleDto create(RoleDto userDto) {
        Role role = Role.builder()
                .roleNameNepali(userDto.getRoleNameNepali())
                .roleNameEnglish(userDto.getRoleNameEnglish())
                .isActive(userDto.getIsActive())
                .build();
        role = roleRepo.save(role);
        return new RoleDto(role);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepo.findById(roleDto.getId());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRoleNameEnglish(roleDto.getRoleNameEnglish());
            role.setRoleNameNepali(roleDto.getRoleNameNepali());
            role.setIsActive(roleDto.getIsActive());
            return new RoleDto(roleRepo.save(role));
        } else {
            throw new RuntimeException("user is not present");
        }
    }

    @Override
    public RoleDto findById(Short id) {
        Role role = roleRepo.findById(id).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        return new RoleDto(role);
    }

    @Override
    public List<RoleDto> findALl() {
        List<Role> roles = roleRepo.findAll();
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("user is not found ");
        } else {
            return roles.stream().map(
                    map -> new RoleDto(map)
            ).collect(Collectors.toList());
        }
    }
}
