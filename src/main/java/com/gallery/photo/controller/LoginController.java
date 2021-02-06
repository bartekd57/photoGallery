package com.gallery.photo.controller;

import com.gallery.photo.model.Photo;
import com.gallery.photo.model.User;
import com.gallery.photo.model.dto.UserDTO;
import com.gallery.photo.security.DTO.JwtTokenDTO;
import com.gallery.photo.security.DTO.LoginDTO;
import com.gallery.photo.security.token.JwtProvider;
import com.gallery.photo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    JwtProvider provider;
    AuthenticationManager manager;
    UserService userService;

    @Autowired
    public LoginController(JwtProvider provider, AuthenticationManager manager, UserService userService) {
        this.provider = provider;
        this.manager = manager;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String loginGet(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @ResponseBody
    @PostMapping("/logino")
    public ResponseEntity getTokenForUser(@RequestBody LoginDTO loginDTO){

        Authentication authentication =
                manager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenDTO(token));

    }


    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String login(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword() )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);
        redirectAttributes.addFlashAttribute("user", userDTO);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "";
        if(role.contains("ADMIN")) {
            targetUrl = "redirect:/users";
        } else if(role.contains("USER")) {
            targetUrl = "redirect:/photosForUser";
        }
        return targetUrl;

//        return "redirect:/photos";
    }

    @GetMapping(value = "/photosForUser")
    public String picturesForUser(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) throws Exception {

//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUserByUsername(userDTO.getUsername());
        List<Photo> photos = user.getGallery().getPhotos().stream().distinct().collect(Collectors.toList());

        model.addAttribute("photos", photos);
        model.addAttribute("galleryId", user.getGallery().getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("imgUrl", user.getGallery().getPhotos().stream().findFirst().map(photo -> photo.getImgUrl()).get());

        return "photosForUser";

    }

    @GetMapping(value = "/photos")
    public String pictures(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) throws Exception {

//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUserByUsername(userDTO.getUsername());
        List<Photo> photos = user.getGallery().getPhotos().stream().distinct().collect(Collectors.toList());

        model.addAttribute("photos", photos);
        model.addAttribute("galleryId", user.getGallery().getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("imgUrl", user.getGallery().getPhotos().stream().findFirst().map(photo -> photo.getImgUrl()).get());

        return "photos";

    }

}
