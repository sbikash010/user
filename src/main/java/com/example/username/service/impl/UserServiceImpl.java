package com.example.username.service.impl;

import com.example.username.dto.user.ResponseUser;
import com.example.username.dto.user.RoleUserDto;
import com.example.username.dto.user.UserDto;
import com.example.username.dto.user.UserResponse;
import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.dto.userImage.UserImageResponse;
import com.example.username.mapper.UserMapper;
import com.example.username.mapper.UserRoleMapper;
import com.example.username.model.Role;
import com.example.username.model.User;
import com.example.username.model.UserImages;
import com.example.username.repo.RoleRepo;
import com.example.username.repo.UserImageRepo;
import com.example.username.repo.UserRepo;
import com.example.username.service.UserService;
import com.example.username.utils.ApacheTikaFileUtil;
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
    private final UserRoleMapper userRoleMapper;
//    private final ApacheTikaFileUtil apacheTikaFileUtil;

//    private final SaveImage saveImage;

    @Override
    public void create(UserDto userDto) {

        List<User> userList=new ArrayList<>();
        for(RoleUserDto roleUserDto:userDto.getRoles())
        {
         Role role=roleRepo.findById(roleUserDto.getRoleId()).orElseThrow(
                 ()->new RuntimeException("role is not found !!!")
         );
            User user = User.builder()
                    .userFullNameEnglish(userDto.getUserFullNameEnglish())
                    .userFullNameNepali(userDto.getUserFullNameNepali())
                    .userName(userDto.getUserName())
                    .userEmail(userDto.getUserEmail())
                    .userPhone(userDto.getUserPhone())
                    .isActive(userDto.getIsActive())
                    .roles(role)
                    .build();
            userList.add(user);
        }
        userRepo.saveAll(userList);


//        User user = User.builder()
//                .userFullNameEnglish(userDto.getUserFullNameEnglish())
//                .userFullNameNepali(userDto.getUserFullNameNepali())
//                .userName(userDto.getUserName())
//                .userEmail(userDto.getUserEmail())
//                .userPhone(userDto.getUserPhone())
//                .isActive(userDto.getIsActive())
//                .roles(userDto.getRoles())
//                .build();

    }

    @Override
    public UserImageResponse create(UserImageRequest userImageRequest) throws Exception {
//        String imagePath = null;
        UserImages userImages;
        User user = userRepo.findById(userImageRequest.getUserId()).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        MultipartFile multipartFile = userImageRequest.getMultipartFile();
        String fileOriginName = multipartFile.getOriginalFilename();
//        String imagePath = apacheTikaFileUtil.fileSizeAndTypeValidation(userImageRequest);
        String imagePath = fIleStorageUtils.storeFile(userImageRequest);
//        imagePath = saveImage.storeUserImages(userImageRequest.getMultipartFile());

//        if (userImageRequest.getParentId() != null) {
//
//            UserImages parent = userImageRepo.findById(userImageRequest.getParentId();
//
//            userImages.setParent(parent);
//        }
        userImages = UserImages.builder()
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
        if(path.contains("pdf"))
        {
            fIleStorageUtils.getPDF(path, httpServletResponse);
        }else {
            fIleStorageUtils.getImage(path, httpServletResponse);
        }
    }

    @Override
    public UserDto update(UserDto userDto) {
        List<Role> roles=new ArrayList<>();
        Optional<User> optionalUser = userRepo.findById(userDto.getId());
        Optional<Role> role=roleRepo.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserFullNameEnglish(userDto.getUserFullNameEnglish());
            user.setUserFullNameNepali(userDto.getUserFullNameNepali());
            user.setUserName(userDto.getUserName());
            user.setUserEmail(userDto.getUserEmail());
            user.setUserPhone(userDto.getUserPhone());
            user.setIsActive(userDto.getIsActive());
//            user.setRoles(userDto.getRoles());
            return new UserDto(userRepo.save(user));
        } else {
            throw new RuntimeException("user is not present");
        }
    }

    @Override
    public UserResponse findById(Short id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        return new UserResponse(user);
    }

    @Override
    public List<ResponseUser> findALl() {
       return userRoleMapper.findAll();
    }

//    @Override
//    public UserDto findById(Short id) {
//        User user = userRepo.findById(id).orElseThrow(
//                () -> new RuntimeException("user is not found !!!")
//        );
//        return this.userMapper.findById(id);

//    }

//    @Override
//    public List<UserResponse> findALl() {
//        List<User> users = userRepo.findAll();
//        if (users == null || users.isEmpty()) {
//            throw new RuntimeException("user is not found ");
//        } else {
//            return users.stream().map(
//                    user -> new UserResponse(user)
//            ).collect(Collectors.toList());
//        }
//    }
}
