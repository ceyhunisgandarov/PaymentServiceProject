package com.payment.service.controller;

import com.payment.service.entity.response.RespCategory;
import com.payment.service.response.Response;
import com.payment.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    @GetMapping("/get/main")
    public Response<List<RespCategory>> get() {
        return categoryService.getCategories();
    }

}
