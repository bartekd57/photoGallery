package com.gallery.photo.service;

import com.gallery.photo.message.request.SignupDTO;
import com.gallery.photo.model.User;
import com.gallery.photo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
