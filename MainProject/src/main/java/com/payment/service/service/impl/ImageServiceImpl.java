package com.payment.service.service.impl;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.Company;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.ServiceCategory;
import com.payment.service.entity.request.ReqCategoryImage;
import com.payment.service.entity.response.RespCategoryImage;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.CategoryImageRepository;
import com.payment.service.repository.CategoryRepository;
import com.payment.service.repository.CompanyImageRepository;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final CategoryImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyImageRepository companyImageRepository;
    private final CompanyRepository companyRepository;

    @Override
    public CategoryImage saveImage(MultipartFile file, Long id) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ServiceCategory category = categoryRepository.findServiceCategoryById(id);
        String path = "http://localhost:8000/payment/company/service/images/" + fileName;
        CategoryImage image = new CategoryImage(fileName, file.getContentType(), path, file.getBytes(), category);
        return imageRepository.save(image);
    }

    @Override
    public BufferedImage getImage(String filename) {
        BufferedImage imagePng = null;
        CategoryImage image = imageRepository.findCategoryImageByName(filename);
        byte[] imageData = image.getImage();
        try {
            imagePng = bytesToImage(imageData);
        } catch (IOException e) {
            System.err.println("Byte dizisi resme dönüştürülürken bir hata oluştu: " + e.getMessage());
        }

        return imagePng;
    }

    @Override
    public CategoryImage getImageByName(String filename) {
        return imageRepository.findCategoryImageByName(filename);
    }

    @Override
    public CompanyImage getCompanyImageByName(String filename) {
        return companyImageRepository.findCompanyImageByName(filename);
    }


    @Override
    public CompanyImage saveCompanyImage(MultipartFile file, Long id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Company company = companyRepository.findCompanyById(id);
        String path = "http://localhost:8000/payment/company/service/images/company/" + fileName;
        CompanyImage image = new CompanyImage(fileName, file.getContentType(), path, file.getBytes(), company);
        return companyImageRepository.save(image);
    }

    public static BufferedImage bytesToImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return ImageIO.read(bis);
    }

}
