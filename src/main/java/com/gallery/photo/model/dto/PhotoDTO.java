package com.gallery.photo.model.dto;

import com.gallery.photo.model.Gallery;

public class PhotoDTO {

    private Long id;
    private String photoName;
    private String imgUrl;
    private Gallery gallery;

    public PhotoDTO() {
    }

    public PhotoDTO(Long id, String photoName, String imgUrl, Gallery gallery) {
        this.id = id;
        this.photoName = photoName;
        this.imgUrl = imgUrl;
        this.gallery = gallery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
