package com.gallery.photo.model.mapper;

import com.gallery.photo.model.User;
import com.gallery.photo.security.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToDto(User user);
    User dtoToUser(UserDTO userDTO);
}
