package com.example.username.mapper;

import com.example.username.dto.user.ResponseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<ResponseUser> findAll();
}
