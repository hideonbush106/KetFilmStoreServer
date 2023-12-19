package com.sukute1712.ketfilmstore.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sukute1712.ketfilmstore.entity.Image;
import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.exception.ResourceNotFoundException;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ImageService {

    ImageRepository imageRepository;
    Cloudinary cloudinary;
    UserService userService;

    public Message uploadImage(MultipartFile imageSource, String publicId, HttpServletRequest request) throws IOException {

        User user = userService.getAuthenticatedUser(request);

        cloudinary.uploader().upload(imageSource.getBytes(), ObjectUtils.asMap("public_id", publicId));
        String url = cloudinary.url().generate(publicId + ".jpg");
        Image image = new Image();
        image.setSource(url);
        image.setPublicId(publicId);
        image.setCreatedDate(LocalDateTime.now());
        image.setCreatedBy(user);
        imageRepository.save(image);
        return new Message("Image uploaded successfully");
    }

    public Message renameImage(String publicId, String newPublicId, HttpServletRequest request) throws IOException {

        User user = userService.getAuthenticatedUser(request);

        cloudinary.uploader().rename(publicId, newPublicId, ObjectUtils.emptyMap());
        String url = cloudinary.url().generate(newPublicId + ".jpg");
        Image image = imageRepository.findByPublicId(publicId).orElseThrow(() -> new ResourceNotFoundException("Image"));
        image.setSource(url);
        image.setPublicId(newPublicId);
        image.setLastModifiedDate(LocalDateTime.now());
        image.setLastModifiedBy(user);
        imageRepository.save(image);
        return new Message("Image renamed successfully");
    }

    public Message deleteImage(String publicId) throws IOException {
        imageRepository.findByPublicId(publicId).orElseThrow(() -> new ResourceNotFoundException("Image"));
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return new Message("Image deleted successfully");
    }

}
