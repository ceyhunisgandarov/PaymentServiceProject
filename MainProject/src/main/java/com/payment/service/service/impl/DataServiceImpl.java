package com.payment.service.service.impl;

import com.payment.service.entity.ReqField;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.repository.OperationRepository;
import com.payment.service.repository.RequestHtmlRepository;
import com.payment.service.response.Response;
import com.payment.service.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final RequestHtmlRepository requestRepository;
    private final CompanyRepository companyRepository;
    private final OperationRepository operationRepository;

    @Override
    public Response<ReqField> getHtmlCode(String bankName, String operationName) {
        Response<ReqField> response = new Response<>();

//        try {
//            if (bankName==null && operationName==null) {
//                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
//            }
//            Company company = companyRepository.findCompanyByCompanyLink(bankName);
//            if (company==null) {
//                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Company not found");
//            }
//            Operation operation = operationRepository.findOperationByCompanyAndOperationLink(company, operationName);
//            if (operation==null) {
//                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Operation not found");
//            }
//            RequestHtml requestHtml = requestRepository.findRequestHtmlByOperation(operation);
//            if (requestHtml==null) {
//                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Operation not found");
//            }
//            response.setT(requestHtml);
//            response.setStatus(ResponseStatus.getSuccessMessage());
//        } catch (MainException ex) {
//            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
//        } catch (Exception ex) {
//            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
//        }

        return response;
    }
}
