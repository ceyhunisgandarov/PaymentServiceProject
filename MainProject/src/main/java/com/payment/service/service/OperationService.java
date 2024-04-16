package com.payment.service.service;

import com.payment.service.entity.response.RespOperation;
import com.payment.service.response.Response;

import java.util.List;

public interface OperationService {

    Response<List<RespOperation>> getOperationsByCompanyName(String companyName);

}
