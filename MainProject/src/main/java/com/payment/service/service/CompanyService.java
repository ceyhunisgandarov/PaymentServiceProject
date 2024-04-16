package com.payment.service.service;

import com.payment.service.entity.response.RespCompany;
import com.payment.service.response.Response;

import java.util.List;

public interface CompanyService {

    Response<List<RespCompany>> getCompaniesByCategory(String categoryName);

    Response<RespCompany> getCompanyByName(String companyName);

}
