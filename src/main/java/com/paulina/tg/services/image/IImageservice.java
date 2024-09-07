package com.paulina.tg.services.image;

import com.paulina.tg.dto.ImageDto;
import com.paulina.tg.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageservice {
    Image getImageById(String id);
    void deleteImageById(String id);
    List<ImageDto> saveImages(String productId, List<MultipartFile> files);
    void updateImage(MultipartFile file, String imageId);
}
