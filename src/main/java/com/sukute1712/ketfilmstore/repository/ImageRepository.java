package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findByPublicId(String publicId);

}
