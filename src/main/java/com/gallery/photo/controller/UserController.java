package com.gallery.photo.controller;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.dto.UserDTO;
import com.gallery.photo.repository.GalleryRepository;
import com.gallery.photo.repository.PhotoRepository;
import com.gallery.photo.service.UserService;
import com.gallery.photo.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    UserService userService;
    GalleryRepository galleryRepository;
    PhotoRepository photoRepository;

    @Autowired
    public UserController(UserService userService, GalleryRepository galleryRepository, PhotoRepository photoRepository) {
        this.userService = userService;
        this.galleryRepository = galleryRepository;
        this.photoRepository = photoRepository;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.getAllUsers();

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
        model.addAttribute("username", user.getUsername());
        model.addAttribute("galleryId", user.getGallery().getId());
        return "photos";
    }

    @PostMapping("/addPhoto/{id}")
    public String addPhoto(@PathVariable Long id, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        UserDTO userDTO = userService.getUserById(id);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        List<Photo> photos = userDTO.getGallery().getPhotos();
//        photos.forEach(photo -> {
//            String name = fileName;
//            if(photo.getImgUrl().equals(fileName)) name = name + (++counter);
//            return name;
//        }
//        );


        photos.add(new Photo(fileName, fileName, userDTO.getGallery()));
        userDTO.getGallery().setPhotos(photos);

        String uploadDir = "user-photos/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        userService.saveUser(userDTO);

        return "redirect:/userGallery/"+ id;
    }

    @GetMapping("/deletePhoto/{id}")
    public String deletePhoto(@PathVariable Long id) {
        photoRepository.deleteById(id);
//        Long userId = photoRepository.findById(id).orElse(null).getGallery().getUser().getId();
        return "redirect:/userGallery/"+ id;
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
