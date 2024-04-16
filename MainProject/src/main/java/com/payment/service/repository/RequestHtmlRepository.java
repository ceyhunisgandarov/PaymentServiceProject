package com.payment.service.repository;

import com.payment.service.entity.Operation;
import com.payment.service.entity.ReqField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHtmlRepository extends JpaRepository<ReqField, Long> {

    ReqField findRequestHtmlByOperation(Operation operation);

}
