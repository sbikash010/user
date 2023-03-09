package com.example.username.service;

import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.dto.userImage.UserImageResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserImageService {
    UserImageResponse create(UserImageRequest userImageRequest) throws Exception;

    UserImageResponse update(UserImageRequest userImageRequest);

    UserImageResponse findById(Short id);

    List<UserImageResponse> findALl();

    void imageFindById(Short id, HttpServletResponse httpServletResponse) throws IOException;
}
