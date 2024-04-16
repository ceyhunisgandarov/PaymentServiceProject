package com.payment.service.entity.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespRespField {

    private Long id;
    private String name;
    private String maskTrue;
    private String fieldName;

}
