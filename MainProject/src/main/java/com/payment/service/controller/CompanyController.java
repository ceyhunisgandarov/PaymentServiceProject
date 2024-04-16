package com.payment.service.controller;

import com.payment.service.entity.response.RespCompany;
import com.payment.service.entity.response.RespOperation;
import com.payment.service.response.Response;
import com.payment.service.service.CompanyService;
import com.payment.service.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/get/companies")
public class CompanyController {

    private final OperationService operationService;
    private final CompanyService companyService;

    @GetMapping("/{companyName}")
    public Response<List<RespOperation>> getOperations(@PathVariable String companyName) {
        return operationService.getOperationsByCompanyName(companyName);
    }

    @GetMapping("/company/{companyName}")
    public Response<RespCompany> getCompanyByName(@PathVariable String companyName) {
        return companyService.getCompanyByName(companyName);
    }


}
