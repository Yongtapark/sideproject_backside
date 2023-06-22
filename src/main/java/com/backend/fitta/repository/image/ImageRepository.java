package com.backend.fitta.repository.image;


import com.backend.fitta.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Long> {
}
