package com.payment.service.controller;

import com.payment.service.entity.response.RespReqField;
import com.payment.service.entity.response.RespRespField;
import com.payment.service.response.Response;
import com.payment.service.service.RequestResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final RequestResponseService reqRespService;

    @GetMapping("/get/response/{companyName}/{operationName}")
    public Response<List<RespRespField>> getResponsePageHtml(@PathVariable String companyName, @PathVariable String operationName) {
        return reqRespService.getResponse(companyName, operationName);
    }

    @GetMapping("/get/request/{companyName}/{operationName}")
    public Response<List<RespReqField>> getRequestPageHtml(@PathVariable String companyName, @PathVariable String operationName) {
        return reqRespService.getRequest(companyName, operationName);
    }

}
