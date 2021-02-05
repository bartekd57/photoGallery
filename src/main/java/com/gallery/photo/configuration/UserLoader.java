package com.gallery.photo.configuration;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.Role;
import com.gallery.photo.model.RoleName;
import com.gallery.photo.model.User;
import com.gallery.photo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {

        Set<Role> roles = new HashSet<>();
        Role role= new Role();
        role.setName(RoleName.ROLE_ADMIN);
        roles.add(role);
        User user = new User("admin", "admin@gmail.com", roles);
        user.setPassword(passwordEncoder.encode("admin123"));
        userRepository.save(user);


        Set<Role> userRoles1 = new HashSet<>();
        Role userRole1 = new Role();
        userRole1.setName(RoleName.ROLE_USER);
        userRoles1.add(userRole1);

        User user1 = new User("user1",  "user1@gmail.com", userRoles1);
        user1.setPassword(passwordEncoder.encode("pass1"));
        user1.setGallery(new Gallery("gallery1"));
        List<Photo> photos1 = new ArrayList<>();
        photos1.add(new Photo("photo1", "1.jpeg", user1.getGallery()));
        photos1.add(new Photo("photo2", "2.jpeg", user1.getGallery()));
        photos1.add(new Photo("photo3", "3.jpeg", user1.getGallery()));
        user1.getGallery().setPhotos(photos1);

        Set<Role> userRoles2 = new HashSet<>();
        Role userRole2 = new Role();
        userRole2.setName(RoleName.ROLE_USER);
        userRoles2.add(userRole2);

        User user2 = new User("user2", "user2@gmail.com", userRoles2);
        user2.setPassword(passwordEncoder.encode("pass2"));
        user2.setGallery(new Gallery("gallery2"));
        List<Photo> photos2 = new ArrayList<>();
        photos2.add(new Photo("photo4", "4.jpeg", user2.getGallery()));
        photos2.add(new Photo("photo5", "5.jpeg", user2.getGallery()));
        photos2.add(new Photo("photo6", "6.jpeg", user2.getGallery()));
        user2.getGallery().setPhotos(photos2);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
