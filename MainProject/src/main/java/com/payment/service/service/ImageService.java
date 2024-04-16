package com.payment.service.service;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.request.ReqCategoryImage;
import com.payment.service.entity.response.RespCategoryImage;
import com.payment.service.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageService {
    CategoryImage saveImage(MultipartFile image, Long id) throws Exception;

    BufferedImage getImage(String filename);

    public CategoryImage getImageByName(String filename);

    public CompanyImage getCompanyImageByName(String filename);


    CompanyImage saveCompanyImage(MultipartFile file, Long id) throws IOException;
}
