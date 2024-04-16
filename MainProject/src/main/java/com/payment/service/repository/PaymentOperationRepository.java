package com.payment.service.repository;

import com.payment.service.entity.Operation;
import com.payment.service.entity.PaymentOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOperationRepository extends JpaRepository<PaymentOperation, Long> {

    PaymentOperation findPaymentOperationByOperation(Operation operation);

    PaymentOperation findPaymentOperationByName(String name);

}
