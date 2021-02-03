package com.gallery.photo.service;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.dto.PhotoDTO;
import com.gallery.photo.model.mapper.PhotoMapper;
import com.gallery.photo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void appNewPhoto(String name,String imgUrl, Gallery gallery){

        PhotoDTO photoDTO = new PhotoDTO();

        photoDTO.setPhotoName(name);
        photoDTO.setImgUrl(imgUrl);
        photoDTO.setGallery(gallery);

        Photo photo = PhotoMapper.INSTANCE.dtoToPhoto(photoDTO);

        photoRepository.save(photo);
    }

}
