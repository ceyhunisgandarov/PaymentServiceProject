package com.payment.service.controller;

import com.payment.service.response.Response;
import com.payment.service.security.model.ReqAuth;
import com.payment.service.security.model.RespAuth;
import com.payment.service.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public Response<RespAuth> login(@RequestBody ReqAuth request) throws Exception {
        return authService.login(request);
    }

}
