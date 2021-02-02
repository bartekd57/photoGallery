package com.gallery.photo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoName;
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;


    public Photo() {
    }

    public Photo(Long id, String photoName, String imgUrl, Gallery gallery) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(photoName, photo.photoName) &&
                Objects.equals(imgUrl, photo.imgUrl) &&
                Objects.equals(gallery, photo.gallery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoName, imgUrl, gallery);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", gallery=" + gallery +
                '}';
    }
}
