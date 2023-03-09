package com.example.username.controller;

import com.example.username.dto.userImage.UserImageRequest;
import com.example.username.service.UserImageService;
import com.example.username.utils.GlobalAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-image")
@RequiredArgsConstructor
public class UserImageController {

    private final UserImageService userImageService;
//
//    @PostMapping
//    public ResponseEntity<GlobalAPIResponse> create(@Valid @ModelAttribute UserImageRequest userImageRequest, BindingResult bindingResult) throws Exception {
//        MultipartFile multipartFile = userImageRequest.getMultipartFile();
//        Long size = multipartFile.getSize();
//        if (bindingResult.hasErrors()) {
//            String errror = bindingResult.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false, errror, null));
//        } else {
//            return ResponseEntity.ok(new GlobalAPIResponse(true, "user image is add successfully",
//                    userImageService.create(userImageRequest)));
//        }
//    }

    @PutMapping
    public ResponseEntity<GlobalAPIResponse> update(@Valid @ModelAttribute UserImageRequest userImageRequest, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String errror = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false, errror, null));
        } else {
            return ResponseEntity.ok(new GlobalAPIResponse(true, "user image is add successfully", userImageService.update(userImageRequest)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalAPIResponse> findById(@PathVariable Short id) throws Exception {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "user image is fetch successfully", userImageService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<GlobalAPIResponse> findAll() throws Exception {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "user List are fetch successfully", userImageService.findALl()));
    }

//    @GetMapping("/image/{id}")
//    public ResponseEntity<GlobalAPIResponse> imageFindById(@PathVariable Short id, HttpServletResponse httpServletResponse) throws Exception {
//        userImageService.imageFindById(id, httpServletResponse);
//        return ResponseEntity.ok(new GlobalAPIResponse(true, "user image is fetch successfully", null));
//    }
}
