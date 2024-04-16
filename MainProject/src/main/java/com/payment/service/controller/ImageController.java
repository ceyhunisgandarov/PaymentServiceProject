package com.payment.service.controller;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.response.RespCategoryImage;
import com.payment.service.entity.response.RespCompanyImage;
import com.payment.service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.UrlResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/get/image/save/{id}")
    public RespCategoryImage save (@RequestParam("file") MultipartFile file, @PathVariable Long id) throws Exception {
        CategoryImage image = imageService.saveImage(file, id);

        return new RespCategoryImage(image.getName(), image.getType(), image.getGetPath());
    }

    @PostMapping("/get/image/save/company/{id}")
    public RespCompanyImage saveCompanyImage (@RequestParam("file") MultipartFile file, @PathVariable Long id) throws Exception {
        CompanyImage image = imageService.saveCompanyImage(file, id);

        return new RespCompanyImage(image.getName(), image.getType(), image.getGetPath());
    }

    @GetMapping(value = "/get/images/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        CategoryImage categoryImage = imageService.getImageByName(filename);
        if (categoryImage != null) {
            try {
                BufferedImage image = bytesToImage(categoryImage.getImage());
                if (image != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageToBytes(image));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/get/images/company/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getCompanyImage(@PathVariable String filename) {
        CompanyImage companyImage = imageService.getCompanyImageByName(filename);
        if (companyImage != null) {
            try {
                BufferedImage image = bytesToImage(companyImage.getImage());
                if (image != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageToBytes(image));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }

    private BufferedImage bytesToImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return ImageIO.read(bis);
    }

    private byte[] imageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        return bos.toByteArray();
    }

}
