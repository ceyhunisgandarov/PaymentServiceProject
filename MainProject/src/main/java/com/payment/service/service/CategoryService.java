package com.payment.service.service;

import com.payment.service.entity.ServiceCategory;
import com.payment.service.entity.response.RespCategory;
import com.payment.service.entity.response.RespCompany;
import com.payment.service.response.Response;

import java.util.List;

public interface CategoryService {
    Response<List<RespCategory>> getCategories();

    Response<RespCategory> getCategoryByName(String categoryName);
}
