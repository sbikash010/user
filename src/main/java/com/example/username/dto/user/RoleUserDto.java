package com.example.username.dto.user;

import com.example.username.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDto {
    @JsonIgnore
    private Short id;

    private Short roleId;

    private String roleNameEnglish;
    private String roleNameNepali;
    private Boolean isActive;

    public RoleUserDto(User user)
    {
       this.id= user.getId();
       this.roleId=user.getRoles().getId();
       this.roleNameEnglish= user.getRoles().getRoleNameEnglish();
       this.roleNameNepali=user.getRoles().getRoleNameNepali();
       this.isActive=user.getRoles().getIsActive();
    }
}
