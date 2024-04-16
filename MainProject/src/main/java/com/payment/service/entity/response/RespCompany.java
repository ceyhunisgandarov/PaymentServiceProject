package com.payment.service.entity.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespCompany {

    private Long id;
    private String name;
    private String companyLink;
    private String imagePath;
    private String category;
}
