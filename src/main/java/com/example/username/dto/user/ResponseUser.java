package com.example.username.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private Short id;
    private String userFullNameEnglish;
    private String userFullNameNepali;
    private String userName;
    private String userEmail;
    private String userPhone;
    private Boolean isActive;
    private List<RoleUserDto> roleUserDtoList;
}
