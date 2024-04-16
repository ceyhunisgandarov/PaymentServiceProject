package com.payment.service.controller;

import com.payment.service.entity.response.RespCategory;
import com.payment.service.entity.response.RespCompany;
import com.payment.service.response.Response;
import com.payment.service.service.CategoryService;
import com.payment.service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/get/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CompanyService companyService;

    @GetMapping("/{categoryName}")
    public Response<List<RespCompany>> getCompaniesByCategory(@PathVariable String categoryName){
        return companyService.getCompaniesByCategory(categoryName);
    }

    @GetMapping("/category/{categoryName}")
    public Response<RespCategory> getCategoryByName(@PathVariable String categoryName){
        return categoryService.getCategoryByName(categoryName);
    }


}
