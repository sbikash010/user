package com.example.username.service;

import com.example.username.dto.userRole.UserRoleRequest;
import com.example.username.dto.userRole.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    UserRoleResponse create(UserRoleRequest userDto);

    UserRoleResponse update(UserRoleRequest userDto);

    UserRoleResponse findById(Short id);

    List<UserRoleResponse> findALl();
}
