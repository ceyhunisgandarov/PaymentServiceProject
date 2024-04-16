package com.payment.service.service.impl;

import com.payment.service.entity.Company;
import com.payment.service.entity.Operation;
import com.payment.service.entity.ReqField;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.repository.OperationRepository;
import com.payment.service.repository.RequestHtmlRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final CompanyRepository companyRepository;
    private final OperationRepository operationRepository;
    private final RequestHtmlRepository fieldRepository;


    @Override
    public Response<String> getTestData(String bankName, String operationName) {
        Response<String> response = new Response<>();
        Company company = companyRepository.findCompanyByCompanyLink(bankName);
        Operation operation = operationRepository.findOperationByCompanyAndOperationLink(company, operationName);
        ReqField html = fieldRepository.findRequestHtmlByOperation(operation);
        String htmlStr = html.getName();
        response.setT("hello");
        response.setStatus(ResponseStatus.getSuccessMessage());
        return response;
    }
}
