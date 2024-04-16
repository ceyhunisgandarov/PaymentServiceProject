package com.payment.service.repository;

import com.payment.service.entity.CategoryImage;
import com.payment.service.entity.Company;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage, Long> {

    CompanyImage findCompanyImageByCompany(Company company);

    CompanyImage findCompanyImageByName(String name);

}
