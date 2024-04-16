package com.payment.service.entity.response;

import jakarta.persistence.Lob;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespCategory {

    private Long id;
    private String name;
    private String categoryLink;
    private String imagePath;

}
