package com.example.username.controller;

import com.example.username.dto.role.RoleDto;
import com.example.username.dto.userRole.UserRoleRequest;
import com.example.username.model.UserRole;
import com.example.username.repo.UserRoleRepo;
import com.example.username.service.RoleService;
import com.example.username.service.UserRoleService;
import com.example.username.utils.GlobalAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<GlobalAPIResponse> create(@Valid @RequestBody UserRoleRequest userRoleRequest, BindingResult bindingResult)throws  Exception
    {
        if(bindingResult.hasErrors()) {
            String errror=bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false,errror,null));
        }else {
            return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is add successfully", userRoleService.create(userRoleRequest)));
        }
    }
    @PutMapping
    public ResponseEntity<GlobalAPIResponse> update(@Valid @RequestBody  UserRoleRequest userRoleRequest,BindingResult bindingResult)throws  Exception
    {
        if(bindingResult.hasErrors()) {
            String errror=bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false,errror,null));
        }else {
            return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is add successfully", userRoleService.update(userRoleRequest)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalAPIResponse> findById(@PathVariable Short id)throws  Exception
    {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is fetch successfully", userRoleService.findById(id)));
    }
    @GetMapping
    public ResponseEntity<GlobalAPIResponse> findAll()throws  Exception
    {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "Role List are fetch successfully", userRoleService.findALl()));
    }

}
