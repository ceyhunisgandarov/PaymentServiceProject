package com.payment.service.service.impl;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.ServiceCategory;
import com.payment.service.entity.response.RespCategory;
import com.payment.service.entity.response.RespCompany;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.CategoryImageRepository;
import com.payment.service.repository.CategoryRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryImageRepository imageRepository;

    @Override
    public Response<List<RespCategory>> getCategories(){
        Response<List<RespCategory>> response = new Response<>();

        try {
            List<ServiceCategory> categories = categoryRepository.findAll();
            List<RespCategory> respCategories = new ArrayList<>() {};
            for (ServiceCategory category : categories) {
                CategoryImage categoryImage = imageRepository.findCategoryImageByCategory(category);
//                if (categoryImage != null) {
//                    throw new MainException(ExceptionConstant.IMAGE_NOT_FOUND, "image not found");
//                }
                RespCategory respCategory = RespCategory.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .categoryLink(category.getCategoryLink())
                        .imagePath(categoryImage.getGetPath())
                        .build();
                respCategories.add(respCategory);
            }
            response.setT(respCategories);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<RespCategory> getCategoryByName(String categoryName) {
        Response<RespCategory> response = new Response<>();

        try {
            if (categoryName==null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            ServiceCategory category = categoryRepository.findServiceCategoryByCategoryLink(categoryName);
            if (category==null) {
                throw new MainException(ExceptionConstant.CATEGORY_NOT_FOUND, "Category not found");
            }
            CategoryImage image = imageRepository.findCategoryImageByCategory(category);
            RespCategory respCategory = RespCategory.builder()
                    .name(category.getName())
                    .imagePath(image.getGetPath())
                    .build();
            response.setT(respCategory);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }


        return response;
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
