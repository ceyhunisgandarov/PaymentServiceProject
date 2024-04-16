package com.payment.service.repository;

import com.payment.service.entity.AuthorizeRequest;
import com.payment.service.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<AuthorizeRequest, Long> {

    AuthorizeRequest findAuthorizeRequestByCompany(Company company);

}
