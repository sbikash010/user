package com.example.username.dto.userRole;

import com.example.username.model.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleResponse {
    private Short id;

    private Short userId;
    private String userFullNameEnglish;
    private String userFullNameNepali;
    private String userName;
    private String userEmail;
    private String userPhone;

    private Short roleId;
    private String roleNameEnglish;
    private String roleNameNepali;

    public UserRoleResponse(UserRole userRole)
    {
        this.id=userRole.getId();

        this.userId=userRole.getUser().getUId();
        this.userFullNameEnglish=userRole.getUser().getUserFullNameEnglish();
        this.userFullNameNepali=userRole.getUser().getUserFullNameNepali();
        this.userName=userRole.getUser().getUserName();
        this.userEmail=userRole.getUser().getUserEmail();
        this.userPhone=userRole.getUser().getUserPhone();

        this.roleId=userRole.getRole().getId();
        this.roleNameEnglish=userRole.getRole().getRoleNameEnglish();
        this.roleNameNepali=userRole.getRole().getRoleNameNepali();
    }
}
