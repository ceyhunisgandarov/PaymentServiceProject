package com.payment.service.entity.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyData {

    private String bankCardNumber;
    private String agreementNumber;
    private Date customerDob;
    private String cardNumber;
    private BigDecimal amountOfPayment;

}
