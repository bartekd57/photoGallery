package com.gallery.photo.security.service;

import com.gallery.photo.model.Role;
import com.gallery.photo.repository.RoleRepository;
import com.gallery.photo.security.DTO.UserRoleNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(UserRoleNameEnum roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
