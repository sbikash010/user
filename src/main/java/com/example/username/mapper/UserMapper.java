package com.example.username.mapper;

import com.example.username.dto.role.RoleDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    RoleDto findById(Short id);
}
