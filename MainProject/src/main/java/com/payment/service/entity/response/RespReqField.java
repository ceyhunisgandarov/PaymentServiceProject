package com.payment.service.entity.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespReqField {

    private String name;
    private String category;
    private String label;
    private String placeholder;
    private String typeInput;
    private int maxLength;

}
