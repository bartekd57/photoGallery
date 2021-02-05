package com.gallery.photo.controller;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.RoleName;
import com.gallery.photo.model.dto.UserDTO;
import com.gallery.photo.repository.GalleryRepository;
import com.gallery.photo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    UserService userService;
    GalleryRepository galleryRepository;

    @Autowired
    public UserController(UserService userService, GalleryRepository galleryRepository) {
        this.userService = userService;
        this.galleryRepository = galleryRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.getAllUsers().stream()
                .filter(userDTO -> userDTO.getRoles().contains(RoleName.ROLE_USER)).collect(Collectors.toList());

        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/userso", produces = "application/json")
    @ResponseBody
    public List<UserDTO> userso(Model model) {
        return userService.getAllUsers();
    }

    @GetMapping("/userGallery/{id}")
    public String userGallery(Model model, @PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        Gallery gallery = user.getGallery();
        List<Photo> photos = gallery.getPhotos();
        model.addAttribute("photos", photos);
        return "photos";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/editUser")
    public String editUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/users";
    }
    @GetMapping("/editUser")
    public String getEditedUser(Model model, @RequestParam Long id) {
        UserDTO userDTO = userService.getUserById(id);
        model.addAttribute("user", userDTO);
        return "editUser";
    }
}