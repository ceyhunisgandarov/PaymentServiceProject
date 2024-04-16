package com.payment.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_requests")
public class AuthorizeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String loginName;
    private String password;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Company company;

}
