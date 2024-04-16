package com.payment.service.entity.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespCompanyImage {
    private String fileName;
    private String fileType;
    private String getPath;
}
