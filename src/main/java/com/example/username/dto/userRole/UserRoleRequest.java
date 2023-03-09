package com.example.username.dto.userRole;

import com.example.username.constant.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {
    private Short id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @Min(value = 1, message = FieldErrorConstants.MIN_VALUE)
    private Short userId;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @Min(value = 1, message = FieldErrorConstants.MIN_VALUE)
    private Short roleId;
}
