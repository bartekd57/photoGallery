package com.gallery.photo.security.DTO;

import com.gallery.photo.model.Role;

import java.util.Set;

public class UserDTO {


    private Long id;
    private String userName;
    private String password;
    private UserRoleNameEnum roleName;
    private Set<Role> roles;
    private String photos;

    public UserDTO(Long id, String userName, String password, UserRoleNameEnum roleName, Set<Role> roles, String photos) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
        this.roles = roles;
        this.photos = photos;
    }

    public UserDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleNameEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoleNameEnum roleName) {
        this.roleName = roleName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

}
