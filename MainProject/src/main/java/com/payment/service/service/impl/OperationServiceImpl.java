package com.payment.service.service.impl;

import com.payment.service.entity.Company;
import com.payment.service.entity.Operation;
import com.payment.service.entity.PaymentOperation;
import com.payment.service.entity.response.RespOperation;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.repository.OperationRepository;
import com.payment.service.repository.PaymentOperationRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final CompanyRepository companyRepository;
    private final PaymentOperationRepository paymentOperationRepository;

    @Override
    public Response<List<RespOperation>> getOperationsByCompanyName(String companyName) {
        Response<List<RespOperation>> response = new Response<>();

        try {
            if (companyName==null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(companyName);
            if (company==null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Company not found");
            }
            List<Operation> operations = operationRepository.findAllByCompany(company);
            List<RespOperation> respOperations = new ArrayList<>();
            for (Operation operation : operations) {
                RespOperation respOperation = RespOperation.builder()
                        .id(operation.getId())
                        .name(operation.getName())
                        .operationLink(operation.getOperationLink())
                        .company(operation.getCompany().getCompanyLink())
                        .build();
                respOperations.add(respOperation);
            }
            response.setT(respOperations);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }
}
