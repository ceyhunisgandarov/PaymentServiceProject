package com.payment.service.repository;

import com.payment.service.entity.Company;
import com.payment.service.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findOperationByCompanyAndOperationLink(Company company, String operationName);

    Operation findOperationByCompanyAndName(Company company, String operationName);

    List<Operation> findAllByCompany(Company company);

}
