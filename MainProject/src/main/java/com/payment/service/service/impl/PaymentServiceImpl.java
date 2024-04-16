package com.payment.service.service.impl;

import com.payment.service.auth.AuthRequest;
import com.payment.service.entity.AuthorizeRequest;
import com.payment.service.entity.Company;
import com.payment.service.entity.Operation;
import com.payment.service.entity.PaymentOperation;
import com.payment.service.entity.request.ReqCompanyData;
import com.payment.service.entity.response.RespBankData;
import com.payment.service.entity.response.RespOperation;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.repository.OperationRepository;
import com.payment.service.repository.PaymentOperationRepository;
import com.payment.service.repository.RequestRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.PaymentService;
import com.payment.service.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService, Serializable {

    private final Util util;
    private final CompanyRepository companyRepository;
    private final RequestRepository requestRepository;
    private final OperationRepository opRepository;
    private final PaymentOperationRepository paymentOpRepository;

    private final ObjectMapper objectMapper;

    @Override
    public String getTest(String bankName, String operationName) throws Exception {

        Company company = companyRepository.findCompanyByCompanyLink(bankName);
        System.out.println("In service " + company.getName());
        AuthorizeRequest request = requestRepository.findAuthorizeRequestByCompany(company);
        Operation operation = opRepository.findOperationByCompanyAndOperationLink(company, operationName);
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUserName(request.getLoginName());
        authRequest.setPassword(request.getPassword());
        authRequest.setUrl(request.getUrl());
        System.out.println("In service " + authRequest.getUserName() + ", " + authRequest.getPassword());
        String message = util.doGet(operation.getUrl(), authRequest.getUrl(), authRequest);
        return message;
    }

    @Override
    public Response<RespBankData> getData(String bankName, String operationName, ReqCompanyData bankData) {
        Response<RespBankData> response = new Response<>();
        try {
            Company company = companyRepository.findCompanyByCompanyLink(bankName);
            AuthorizeRequest request = requestRepository.findAuthorizeRequestByCompany(company);
            Operation operation = opRepository.findOperationByCompanyAndOperationLink(company, operationName);

            AuthRequest authRequest = AuthRequest.builder()
                    .userName(request.getLoginName())
                    .password(request.getPassword())
                    .build();

            String data = objectMapper.writeValueAsString(bankData);
            String message = util.doPost(operation.getUrl(), data, authRequest, request.getUrl());

            Response<RespBankData> dataResponse = objectMapper.readValue(message, new TypeReference<Response<RespBankData>>() {
            });

            if (dataResponse.getStatus().getCode() == 1) {
                response.setT(dataResponse.getT());
            }

            response.setStatus(dataResponse.getStatus());

        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<RespBankData> payToCard(String bankName, String operationName, ReqCompanyData bankData) {
        Response response = new Response();

        try {
            if (bankName == null && operationName == null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(bankName);
            if (company == null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Bank not found");
            }
            PaymentOperation paymentOperation = paymentOpRepository.findPaymentOperationByName(operationName);
            if (paymentOperation == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Payment operation not found");
            }
            AuthorizeRequest authorizeRequest = requestRepository.findAuthorizeRequestByCompany(company);
            AuthRequest authRequest = AuthRequest.builder()
                    .userName(authorizeRequest.getLoginName())
                    .password(authorizeRequest.getPassword())
                    .build();
            String authUrl = authorizeRequest.getUrl();
            String apiUrl = paymentOperation.getPaymentUrl();
            String dataStr = objectMapper.writeValueAsString(bankData);
            String result = util.doPut(apiUrl, dataStr, authRequest, authUrl);
            response = objectMapper.readValue(result, new TypeReference<Response<RespBankData>>() {
            });
            if (response.getStatus().getCode() == 1) {

                response.setStatus(ResponseStatus.getSuccessMessage());
            }

        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<RespOperation> getOperation(String companyName, String operationName) {
        Response<RespOperation> response = new Response<>();

        try {
            if (companyName == null && operationName == null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(companyName);
            if (company == null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Bank not found");
            }
            Operation operation = opRepository.findOperationByCompanyAndOperationLink(company, operationName);
            if (operation == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Operation not found");
            }
            PaymentOperation paymentOperation = paymentOpRepository.findPaymentOperationByOperation(operation);
            if (paymentOperation == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Payment operation not found");
            }
            RespOperation respOperation = RespOperation.builder()
                    .operationLink(operation.getOperationLink())
                    .company(operation.getCompany().getCompanyLink())
                    .paymentLink(paymentOperation.getName())
                    .build();
            response.setT(respOperation);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }


    private static String[] splitString(String input, String delimiter) {
        return input.split(delimiter);
    }

}
