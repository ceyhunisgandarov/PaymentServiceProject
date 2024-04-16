package com.payment.service.controller;

import com.payment.service.entity.request.ReqCompanyData;
import com.payment.service.entity.response.RespBankData;
import com.payment.service.entity.response.RespOperation;
import com.payment.service.response.Response;
import com.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/get/getData")
    public String testHello() {
        return "Hello Gateway";
    }

    @GetMapping("/get/{companyName}/{operation}/pay")
    public Response<RespOperation> getOperation (@PathVariable String companyName, @PathVariable String operation) throws Exception {
        return paymentService.getOperation(companyName, operation);
    }

    @PostMapping("/get/{companyName}/{operation}/api")
    public Response<RespBankData> getData (@PathVariable String companyName, @PathVariable String operation, @RequestBody ReqCompanyData bankData) throws Exception {
        System.out.println("In controller " + companyName);
        System.out.println(bankData.getBankCardNumber());
        return paymentService.getData(companyName, operation, bankData);
    }

    @PostMapping("/get/{companyName}/{operation}/pay")
    public Response<RespBankData> pay (@PathVariable String companyName, @PathVariable String operation, @RequestBody ReqCompanyData bankData) throws Exception {
        return paymentService.payToCard(companyName, operation, bankData);
    }

}
