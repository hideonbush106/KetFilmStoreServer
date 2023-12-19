package com.sukute1712.ketfilmstore.controller;

import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
public class ImageController {

    ImageService imageService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message> uploadImage(@RequestPart(name = "source") MultipartFile imageSource,
                                               @RequestPart(name = "publicId") String publicId,
                                               HttpServletRequest request) throws IOException {
        return new ResponseEntity<>(imageService.uploadImage(imageSource, publicId, request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Message> renameImage(@RequestParam(name = "publicId") String publicId,
                                               @RequestParam(name = "newPublicId") String newPublicId,
                                               HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(imageService.renameImage(publicId, newPublicId, request));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Message> deleteImage(@PathVariable String publicId) throws IOException {
        return ResponseEntity.ok(imageService.deleteImage(publicId));
    }
}
