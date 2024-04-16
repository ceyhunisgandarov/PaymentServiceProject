package com.payment.service.repository;

import com.payment.service.entity.Operation;
import com.payment.service.entity.ReqField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<ReqField, Long> {

    List<ReqField> findAllByOperation(Operation operation);

}
