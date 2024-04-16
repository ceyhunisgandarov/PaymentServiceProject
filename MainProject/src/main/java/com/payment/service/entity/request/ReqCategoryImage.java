package com.payment.service.entity.request;

import com.payment.service.entity.ServiceCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqCategoryImage {

    private String name;
    private byte[] image;
    private Long categoryId;

}
