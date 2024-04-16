package com.payment.service.service;

import com.payment.service.entity.RespField;
import com.payment.service.entity.response.RespReqField;
import com.payment.service.entity.response.RespHtml;
import com.payment.service.entity.response.RespRespField;
import com.payment.service.response.Response;

import java.util.List;

public interface RequestResponseService {

    Response<List<RespRespField>> getResponse(String bankName, String operationName);

    Response<List<RespReqField>> getRequest(String bankName, String operationName);

}
