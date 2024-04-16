package com.payment.service.repository;

import com.payment.service.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<ServiceCategory, Long> {

    ServiceCategory findServiceCategoryByCategoryLink(String categoryLink);

    ServiceCategory findServiceCategoryById(Long id);

}
