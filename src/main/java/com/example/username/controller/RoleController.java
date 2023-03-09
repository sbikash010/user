package com.example.username.controller;

import com.example.username.dto.role.RoleDto;
import com.example.username.service.RoleService;
import com.example.username.utils.GlobalAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<GlobalAPIResponse> create(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String errror = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false, errror, null));
        } else {
            return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is add successfully", roleService.create(roleDto)));
        }
    }

    @PutMapping
    public ResponseEntity<GlobalAPIResponse> update(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String errror = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobalAPIResponse(false, errror, null));
        } else {
            return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is add successfully", roleService.update(roleDto)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalAPIResponse> findById(@PathVariable Short id) throws Exception {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "Role is fetch successfully", roleService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<GlobalAPIResponse> findAll() throws Exception {
        return ResponseEntity.ok(new GlobalAPIResponse(true, "Role List are fetch successfully", roleService.findALl()));
    }


}
