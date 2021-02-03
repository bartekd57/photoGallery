package com.gallery.photo.model.dto;

import com.gallery.photo.model.Photo;
import com.gallery.photo.model.User;

import java.util.ArrayList;
import java.util.List;

public class GalleryDTO {

    private Long id;
    private String galleryName;
    private User user;
    private List<Photo> photos = new ArrayList<>();
//    cos sosdssos

    public GalleryDTO() {
    }

    public GalleryDTO(Long id, String galleryName, User user, List<Photo> photos) {
        this.id = id;
        this.galleryName = galleryName;
        this.user = user;
        this.photos = photos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
