package com.gallery.photo.model.mapper;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.dto.GalleryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GalleryMapper {

    static GalleryMapper INSTANCE = Mappers.getMapper(GalleryMapper.class);

    GalleryDTO galleryToDto(Gallery gallery);
    Gallery dtoToGallery(GalleryDTO galleryDTO);

}
