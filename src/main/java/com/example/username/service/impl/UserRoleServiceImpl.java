package com.example.username.service.impl;

import com.example.username.dto.role.RoleDto;
import com.example.username.dto.userRole.UserRoleRequest;
import com.example.username.dto.userRole.UserRoleResponse;
import com.example.username.model.Role;
import com.example.username.model.User;
import com.example.username.model.UserRole;
import com.example.username.repo.RoleRepo;
import com.example.username.repo.UserRepo;
import com.example.username.repo.UserRoleRepo;
import com.example.username.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepo userRoleRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserRoleResponse create(UserRoleRequest userRoleRequest) {
        User user=userRepo.findById(userRoleRequest.getUserId()).orElseThrow(
                ()->new RuntimeException("user is not found !!!")
        );
        Role role= roleRepo.findById(userRoleRequest.getRoleId()).orElseThrow(
                ()->new RuntimeException("user is not found !!!")
        );
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();
        userRole = userRoleRepo.save(userRole);
        return new UserRoleResponse(userRole);
    }

    @Override
    public UserRoleResponse update(UserRoleRequest userRoleRequest) {

        Optional<UserRole> optionalUserRole = userRoleRepo.findById(userRoleRequest.getId());
        if (optionalUserRole.isPresent()) {
            UserRole userRole = optionalUserRole.get();

            User user=userRepo.findById(userRoleRequest.getRoleId()).orElseThrow(
                    ()->new RuntimeException("user is not found !!!")
            );
            Role role=roleRepo.findById(userRoleRequest.getUserId()).orElseThrow(
                    ()->new RuntimeException("user is not found !!!")
            );

            userRole.setUser(user);
            userRole.setRole(role);
            return new UserRoleResponse(userRoleRepo.save(userRole));
        } else {
            throw new RuntimeException("user is not present");
        }
    }

    @Override
    public UserRoleResponse findById(Short id) {
        UserRole userRole = userRoleRepo.findById(id).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        return new UserRoleResponse(userRole);
    }

    @Override
    public List<UserRoleResponse> findALl() {
        List<UserRole> userRoles = userRoleRepo.findAll();
        if (userRoles == null || userRoles.isEmpty()) {
            throw new RuntimeException("user is not found ");
        } else {
            return userRoles.stream().map(
                    map -> new UserRoleResponse(map)
            ).collect(Collectors.toList());
        }
    }
}
