package com.gallery.photo.service;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.User;
import com.gallery.photo.model.dto.GalleryDTO;
import com.gallery.photo.model.mapper.GalleryMapper;
import com.gallery.photo.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryService {

    private GalleryRepository galleryRepository;
    private PhotoService photoService;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository, PhotoService photoService) {
        this.galleryRepository = galleryRepository;
        this.photoService = photoService;
    }


    public void createNewGallery(@RequestParam String name, @RequestBody User user) {
        GalleryDTO galleryDTO = new GalleryDTO();

        galleryDTO.setGalleryName(name);
        galleryDTO.setUser(user);
        galleryDTO.setPhotos(new ArrayList<>());

        Gallery gallery = GalleryMapper.INSTANCE.dtoToGallery(galleryDTO);
        galleryRepository.save(gallery);

    }

    public void addPhotoToGallery(@RequestParam Photo photo, @RequestParam Gallery gallery) {

        List<Photo> photos = gallery.getPhotos();
        photos.add(photo);
        gallery.setPhotos(photos);

        galleryRepository.save(gallery);
//                                            Czy tutaj fotki zapisza sie w galerii (przy kaskadach) czy trzeba tez tutaj zapisywac zdjęcia w repo?
    }


}
