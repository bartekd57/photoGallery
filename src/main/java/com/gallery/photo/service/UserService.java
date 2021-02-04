package com.gallery.photo.service;

import com.gallery.photo.model.User;
import com.gallery.photo.model.mapper.UserMapper;
import com.gallery.photo.repository.UserRepository;
import com.gallery.photo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        for (User user : users)
            result.add(UserMapper.INSTANCE.userToDto(user));
        return result;
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper.INSTANCE::userToDto).orElse(null);
    }

    public void saveUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.dtoToUser(userDTO);
        userRepository.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found: " + username));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public boolean ifUserExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean ifUserExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }





}

