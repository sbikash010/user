package com.example.username.service;

import com.example.username.dto.role.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto create(RoleDto userDto);

    RoleDto update(RoleDto userDto);

    RoleDto findById(Short id);

    List<RoleDto> findALl();
}
