package com.example.username.dto.user;

import com.example.username.dto.role.RoleDto;
import com.example.username.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Short id;
    private String userFullNameEnglish;
    private String userFullNameNepali;
    private String userName;
    private String userEmail;
    private String userPhone;
    private Boolean isActive;
//    private List<RoleDto> roles;

    private Short roleId;
    private String roleNameEnglish;
    private String roleNameNepali;
    public UserResponse(User user) {
        this.id = user.getId();
        this.userFullNameEnglish = user.getUserFullNameEnglish();
        this.userFullNameNepali = user.getUserFullNameNepali();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.isActive = user.getIsActive();
//        List<RoleDto> obtainedRole=new ArrayList<>();
//        for (Role role:user.getRoles()){
//            obtainedRole.add(new RoleDto(role));
//        }
//        this.roles=obtainedRole;

        this.roleId=user.getRoles().getId();
        this.roleNameEnglish= user.getRoles().getRoleNameEnglish();
        this.roleNameNepali=user.getRoles().getRoleNameNepali();

    }

}
