package com.payment.service.repository;

import com.payment.service.entity.Company;
import com.payment.service.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByCategory(ServiceCategory category);

    Company findCompanyByCompanyLink(String name);

    Company findCompanyByName(String name);

    Company findCompanyById(Long id);
}
