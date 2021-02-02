package com.gallery.photo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="galleries")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String galleryName;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "gallery")
    private List<Photo> photos = new ArrayList<>();







}
