package com.example.username.dto.userImage;

import com.example.username.constant.FieldErrorConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserImageRequest {
    private Short id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @Min(value = 1, message = FieldErrorConstants.MIN_VALUE)
    private Short userId;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String order;

    private Boolean isActive = true;

    private Boolean isCurrent;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private MultipartFile multipartFile;

//    private short parentId;


}
