package com.payment.service.entity.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespBankData {

    private String bankName;
    private String operationName;
    private String customerName;
    private String customerSurname;
    private String cardNumber;
    private String dept;
    private String name;
    private String agreementNumber;
    private String customerFullName;
    private BigDecimal creditAmount;
    private BigDecimal remainingDept;
    private String accountNumber;
    private String currency;
    private String responseHtmlCode;
    private BigDecimal maxLimit;
    private String transactionId;
    private BigDecimal amount;

}

