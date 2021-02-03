package com.gallery.photo.model.mapper;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Photo;
import com.gallery.photo.model.dto.GalleryDTO;
import com.gallery.photo.model.dto.PhotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PhotoMapper {

    static PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    PhotoDTO photoToDto(Photo photo);
    Photo dtoToPhoto(PhotoDTO photoDTO);
}
