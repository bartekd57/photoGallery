package com.gallery.photo.repository;

import com.gallery.photo.model.Role;
import com.gallery.photo.security.DTO.UserRoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRoleNameEnum roleName);


}
