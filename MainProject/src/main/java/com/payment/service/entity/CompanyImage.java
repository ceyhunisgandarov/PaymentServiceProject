package com.payment.service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_company_image")
public class CompanyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String getPath;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Company company;

    public CompanyImage(String name, String type, String getPath, byte[] image, Company company) {
        this.name = name;
        this.type = type;
        this.getPath = getPath;
        this.image = image;
        this.company = company;
    }
}
