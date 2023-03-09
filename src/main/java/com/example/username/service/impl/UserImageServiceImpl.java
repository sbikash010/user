package com.example.username.service.impl;

import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.dto.userImage.UserImageResponse;
import com.example.username.model.User;
import com.example.username.model.UserImages;
import com.example.username.repo.UserImageRepo;
import com.example.username.repo.UserRepo;
import com.example.username.service.UserImageService;
import com.example.username.utils.FIleStorageUtils;
import com.example.username.utils.SaveImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    private final UserImageRepo userImageRepo;
    private final UserRepo userRepo;
    private final FIleStorageUtils fIleStorageUtils;
    private final SaveImage saveImage;

    @Override
    public UserImageResponse create(UserImageRequest userImageRequest) throws Exception {
//        String imagePath = null;
        User user = userRepo.findById(userImageRequest.getUserId()).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        MultipartFile multipartFile = userImageRequest.getMultipartFile();
        String fileOriginName = multipartFile.getOriginalFilename();
        String imagePath=fIleStorageUtils.storeFile(userImageRequest);
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
    public UserImageResponse update(UserImageRequest userImageRequest) {

        Optional<UserImages> optionalUserImages = userImageRepo.findById(userImageRequest.getId());
        if (optionalUserImages.isPresent()) {
            UserImages userImages = optionalUserImages.get();

            User user = userRepo.findById(userImageRequest.getUserId()).orElseThrow(
                    () -> new RuntimeException("user is not found !!!")
            );

            MultipartFile multipartFile = userImageRequest.getMultipartFile();
            String fileOriginName = multipartFile.getOriginalFilename();
            String imagePath = fIleStorageUtils.storeFile(userImageRequest);


            userImages.setUser(user);
            userImages.setImagePath(imagePath);
            userImages.setOriginalFileName(fileOriginName);
            userImages.setOrderNo(userImageRequest.getOrder());
            userImages.setIsActive(userImageRequest.getIsActive());
            userImages.setIsCurrent(userImageRequest.getIsCurrent());

            return new UserImageResponse(userImageRepo.save(userImages));
        } else {
            throw new RuntimeException("user is not present");
        }
    }

    @Override
    public UserImageResponse findById(Short id) {
        UserImages userImages = userImageRepo.findById(id).orElseThrow(
                () -> new RuntimeException("user is not found !!!")
        );
        return new UserImageResponse(userImages);
    }

    @Override
    public List<UserImageResponse> findALl() {
        List<UserImages> userImages = userImageRepo.findAll();
        if (userImages == null || userImages.isEmpty()) {
            throw new RuntimeException("user is not found ");
        } else {
            return userImages.stream().map(
                    map -> new UserImageResponse(map)
            ).collect(Collectors.toList());
        }
    }

    @Override
    public void imageFindById(Short id, HttpServletResponse httpServletResponse) throws IOException {
        String path = userImageRepo.getImagePathByUserId(id)
                .orElseThrow(() -> new RuntimeException("No image found for user with id: " + id));
        saveImage.getImage(path, httpServletResponse);
    }
}
