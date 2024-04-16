package com.payment.service.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqAuth {

    private String username;
    private String password;

}
