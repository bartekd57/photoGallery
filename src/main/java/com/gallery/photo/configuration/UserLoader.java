package com.gallery.photo.configuration;

import com.gallery.photo.model.Role;
import com.gallery.photo.model.RoleName;
import com.gallery.photo.model.User;
import com.gallery.photo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {

        Set<Role> roles = new HashSet<>();
        Role role= new Role();
        role.setName(RoleName.ROLE_ADMIN);
        roles.add(role);
        userRepository.save(new User("admin", "admin123", "admin@gmail.com", roles));
    }
}
