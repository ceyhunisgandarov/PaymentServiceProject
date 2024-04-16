package com.payment.service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_image")
public class CategoryImage {
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
    private ServiceCategory category;

    public CategoryImage(String name, String type, String getPath, byte[] image, ServiceCategory category) {
        this.name = name;
        this.type = type;
        this.getPath = getPath;
        this.image = image;
        this.category = category;
    }
}
