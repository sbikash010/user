package com.example.username.dto.user;

import com.example.username.constant.FieldErrorConstants;
import com.example.username.model.Role;
import com.example.username.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Short id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String userFullNameEnglish;


    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String userFullNameNepali;


    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String userName;


    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String userEmail;


    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String userPhone;

    private Boolean isActive;
    private List<RoleUserDto> roles;

//    private Set<Short> rolesid;

    public UserDto(User user) {
        this.id = user.getId();
        this.userFullNameEnglish = user.getUserFullNameEnglish();
        this.userFullNameNepali = user.getUserFullNameNepali();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.isActive = user.getIsActive();
//        Set<Short> roles = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            roles.add(role.getId());
//        }
//        this.rolesid=roles;

    }
}
