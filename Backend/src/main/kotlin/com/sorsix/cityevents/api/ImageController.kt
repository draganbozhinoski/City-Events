package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Image
import com.sorsix.cityevents.repository.ImagesRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/images")
class ImageController(val imagesRepository: ImagesRepository) {
    @PostMapping("/save")
    fun saveImage(file:MultipartFile):ResponseEntity<Image> {
        return ResponseEntity.ok().body(imagesRepository.save(Image(name=file.originalFilename,bytes=file.bytes,type=file.contentType)))
    }
}