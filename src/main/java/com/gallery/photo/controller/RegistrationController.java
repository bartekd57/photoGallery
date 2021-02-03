package com.gallery.photo.controller;

import com.gallery.photo.message.request.SignupDTO;
import com.gallery.photo.model.Role;
import com.gallery.photo.model.RoleName;
import com.gallery.photo.model.User;
import com.gallery.photo.repository.RoleRepository;
import com.gallery.photo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class RegistrationController {

//Narazie zrobilem jak w przykladzie ze zwraca responseentity, ponzniej trzeba przerobic na widoki


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupDTO signupDTO) {
        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            return new ResponseEntity<>("Username already used", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return new ResponseEntity<>("Email already used", HttpStatus.BAD_REQUEST);
        }

        User user = new User(signupDTO.getUsername(), signupDTO.getEmail(),
                passwordEncoder.encode(signupDTO.getPassword()));

        HashSet<Role> roles = new HashSet<>();

        signupDTO.getRoles().forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Admin role not found"));
                    roles.add(adminRole);
                    break;
                case "user":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("User role not found"));
                    roles.add(userRole);
                    break;
            }
        });
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User registered.");
    }


}
