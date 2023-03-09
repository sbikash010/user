package com.example.username.dto.role;

import com.example.username.constant.FieldErrorConstants;
import com.example.username.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RoleDto {

    private Short id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String roleNameEnglish;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private String roleNameNepali;

    private Boolean isActive;
    public  RoleDto(Role role)
    {
        this.id= role.getId();
        this.roleNameEnglish=role.getRoleNameEnglish();
        this.roleNameNepali=role.getRoleNameNepali();
        this.isActive=role.getIsActive();
    }
}
