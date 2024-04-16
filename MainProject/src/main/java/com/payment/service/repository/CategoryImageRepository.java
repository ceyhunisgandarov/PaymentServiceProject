package com.payment.service.repository;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.ServiceCategory;
import com.payment.service.service.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {

    CategoryImage findCategoryImageByCategory(ServiceCategory category);

    CategoryImage findCategoryImageByName(String name);
}
