package com.ei.math.endpoint;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/pub/images")
public class ImageController {

    @GetMapping("/envelope-image")
    public ResponseEntity<byte[]> getSuaImagem() throws IOException {
        String imagePath = "static/img/envelope.png";
        Resource resource = new ClassPathResource(imagePath);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    
}