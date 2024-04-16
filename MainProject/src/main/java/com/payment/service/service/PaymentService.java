package com.payment.service.service;


import com.payment.service.entity.request.ReqCompanyData;
import com.payment.service.entity.response.RespBankData;
import com.payment.service.entity.response.RespOperation;
import com.payment.service.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentService {

    String getTest(String bankName, String operation) throws Exception;

    Response<RespBankData> getData(String bankName, String operation, ReqCompanyData bankData) throws JsonProcessingException, Exception;

    Response<RespBankData> payToCard(String bankName, String operationName, ReqCompanyData bankData);

    Response<RespOperation> getOperation(String companyName, String operation);
}
