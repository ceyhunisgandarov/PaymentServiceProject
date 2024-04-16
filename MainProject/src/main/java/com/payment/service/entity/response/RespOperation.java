package com.payment.service.entity.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespOperation {

    private Long id;
    private String name;
    private String operationLink;
    private String company;
    private String paymentLink;

}
