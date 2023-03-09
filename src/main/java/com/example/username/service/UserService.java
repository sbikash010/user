package com.example.username.service;

import com.example.username.dto.user.UserDto;
import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.dto.userImage.UserImageResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    UserImageResponse create(UserImageRequest userImageRequest) throws Exception;

    void imageFindById(Short id, HttpServletResponse httpServletResponse) throws IOException;

    UserDto update(UserDto userDto);

    UserDto findById(Short id);

    List<UserDto> findALl();
}
