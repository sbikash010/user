package com.example.username.service.impl;

import com.example.username.dto.user.UserDto;
import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.dto.userImage.UserImageResponse;
import com.example.username.model.Role;
import com.example.username.model.User;
import com.example.username.model.UserImages;
import com.example.username.repo.RoleRepo;
import com.example.username.repo.UserImageRepo;
import com.example.username.repo.UserRepo;
import com.example.username.service.UserService;
import com.example.username.utils.FIleStorageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.SecondaryTable;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final UserImageRepo userImageRepo;

    private final FIleStorageUtils fIleStorageUtils;
    private final RoleRepo roleRepo;
//    private final SaveImage saveImage;

    @Override
    public UserDto create(UserDto userDto) {
        Set<Role> roles = new HashSet<>();
        userDto.getRolesid().forEach(roleid -> {
            Role role = roleRepo.findById(roleid)
                    .orElseThrow( () -> new RuntimeException("user is not found !!!"));
            roles.add(role);
        });
//        Role  role=roleRepo.findById().orElseThrow(
//                ()->new RuntimeException("user is not found !!!")
//        );
        User user = User.builder()
                .userFullNameEnglish(userDto.getUserFullNameEnglish())
                .userFullNameNepali(userDto.getUserFullNameNepali())
                .userName(userDto.getUserName())
                .userEmail(userDto.getUserEmail())
                .userPhone(userDto.getUserPhone())
                .isActive(userDto.getIsActive())
                .roles(roles)
                .build();
        user = userRepo.save(user);
        return new UserDto(user);
    }

    @Override
    public UserImageResponse create(UserImageRequest userImageRequest) throws Exception {
//        String imagePath = null;
        User user = userRepo.findById(userImageRequest.getUserId()).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        MultipartFile multipartFile = userImageRequest.getMultipartFile();
        String fileOriginName = multipartFile.getOriginalFilename();
        String imagePath = fIleStorageUtils.storeFile(userImageRequest);
//        imagePath = saveImage.storeUserImages(userImageRequest.getMultipartFile());
        UserImages userImages = UserImages.builder()
                .user(user)
                .imagePath(imagePath)
                .originalFileName(fileOriginName)
                .orderNo(userImageRequest.getOrder())
                .isActive(userImageRequest.getIsActive())
                .isCurrent(userImageRequest.getIsCurrent())
                .build();
        userImages = userImageRepo.save(userImages);
        return new UserImageResponse(userImages);
    }

    @Override
    public void imageFindById(Short id, HttpServletResponse httpServletResponse) throws IOException {

        String path = userImageRepo.getImagePathByUserId(id)
                .orElseThrow(() -> new RuntimeException("No image found for user with id: " + id));
        fIleStorageUtils.getImage(path, httpServletResponse);
    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<User> optionalUser = userRepo.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserFullNameEnglish(userDto.getUserFullNameEnglish());
            user.setUserFullNameNepali(userDto.getUserFullNameNepali());
            user.setUserName(userDto.getUserName());
            user.setUserEmail(userDto.getUserEmail());
            user.setUserPhone(userDto.getUserPhone());
            user.setIsActive(userDto.getIsActive());
            return new UserDto(userRepo.save(user));
        } else {
            throw new RuntimeException("user is not present");
        }
    }

    @Override
    public UserDto findById(Short id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        return new UserDto(user);
    }

    @Override
    public List<UserDto> findALl() {
        List<User> users = userRepo.findAll();
        if (users == null || users.isEmpty()) {
            throw new RuntimeException("user is not found ");
        } else {
            return users.stream().map(
                    user -> new UserDto(user)
            ).collect(Collectors.toList());
        }
    }
}
