package com.payment.service.security.service;

import com.payment.service.response.Response;
import com.payment.service.security.model.ReqAuth;
import com.payment.service.security.model.RespAuth;

public interface AuthService {

    Response<RespAuth> login(ReqAuth request);

}
