package com.payment.service.entity.response;

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
public class RespCategoryImage {

    private String fileName;
    private String fileType;
    private String getPath;

}
