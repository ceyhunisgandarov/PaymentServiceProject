package com.payment.service.service;

import com.payment.service.entity.ReqField;
import com.payment.service.response.Response;

public interface DataService {

    Response<ReqField> getHtmlCode(String bankName, String operationName);

}
