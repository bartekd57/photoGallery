package com.gallery.photo.security.DTO;

public enum UserRoleNameEnum {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");


    private String message;

    UserRoleNameEnum(String message) {
        this.message = message;
    }
}
