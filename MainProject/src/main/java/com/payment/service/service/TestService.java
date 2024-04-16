package com.payment.service.service;

import com.payment.service.response.Response;

public interface TestService {
    Response<String> getTestData(String bankName, String operation);
}
