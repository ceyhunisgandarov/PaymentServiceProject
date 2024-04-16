package com.payment.service.repository;

import com.payment.service.entity.Operation;
import com.payment.service.entity.RespField;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespFieldRepository extends JpaRepository<RespField, Long> {

    List findAllByOperation(Operation operation);
}
