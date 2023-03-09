package com.example.username.dto.userImage;

import com.example.username.model.UserImages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class UserImageResponse {
    private Short id;

    private Short userId;
    private String userFullNameEnglish;
    private String userFullNameNepali;
    private String userName;
    private String userEmail;
    private String userPhone;

    private String order;

    private Boolean isActive;

    private Boolean isCurrent;

    private String imagePath;

    private String originalFileName;



    public  UserImageResponse(UserImages userImages)
    {
        this.id=userImages.getId();

        this.userId=userImages.getUser().getUId();
        this.userFullNameEnglish=userImages.getUser().getUserFullNameEnglish();
        this.userFullNameNepali=userImages.getUser().getUserFullNameNepali();
        this.userName=userImages.getUser().getUserName();
        this.userEmail=userImages.getUser().getUserEmail();
        this.userPhone=userImages.getUser().getUserPhone();

        this.order=userImages.getOrderNo();
        this.isActive=userImages.getIsActive();
        this.isCurrent=userImages.getIsCurrent();
        this.imagePath=userImages.getImagePath();
        this.originalFileName=userImages.getOriginalFileName();

    }
}
