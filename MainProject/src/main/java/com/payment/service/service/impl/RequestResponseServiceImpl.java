package com.payment.service.service.impl;

import com.payment.service.entity.*;
import com.payment.service.entity.response.RespReqField;
import com.payment.service.entity.response.RespRespField;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.*;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.RequestResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestResponseServiceImpl implements RequestResponseService {

    private final CompanyRepository companyRepository;
    private final OperationRepository operationRepository;
    private final RespFieldRepository respField;
    private final FieldRepository requestRepository;

    @Override
    public Response<List<RespRespField>> getResponse(String companyName, String operationName) {
        Response<List<RespRespField>> response = new Response<>();

        try {
            if (companyName == null && operationName == null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(companyName);
            if (company == null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Company not found");
            }
            Operation operation = operationRepository.findOperationByCompanyAndOperationLink(company, operationName);
            if (operation == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Payment operation not found");
            }
            List<RespField> respFields = respField.findAllByOperation(operation);
            if (respFields == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Html  not found");
            }
            List<RespRespField> respRespFields = new ArrayList<>();
            for (RespField field: respFields) {
                RespRespField respRespField = RespRespField.builder()
                        .fieldName(field.getFieldName())
                        .maskTrue(field.getMaskTrue())
                        .name(field.getName())
                        .id(field.getId())
                        .build();
                respRespFields.add(respRespField);
            }
            response.setT(respRespFields);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<List<RespReqField>> getRequest(String companyName, String operationName) {
        Response<List<RespReqField>> response = new Response<>();
        try {
            if (companyName == null && operationName == null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(companyName);
            if (company == null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Company not found");
            }
            Operation operation = operationRepository.findOperationByCompanyAndOperationLink(company, operationName);
            if (operation == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Payment operation not found");
            }
            List<ReqField> reqFields = requestRepository.findAllByOperation(operation);
            if (reqFields == null) {
                throw new MainException(ExceptionConstant.OPERATION_NOT_FOUND, "Field  not found");
            }
            List<RespReqField> respReqFields = new ArrayList<>();
            for (ReqField reqField : reqFields) {
                RespReqField respReqField = RespReqField.builder()
                        .name(reqField.getName())
                        .category(reqField.getCategory())
                        .placeholder(reqField.getPlaceholder())
                        .typeInput(reqField.getTypeInput())
                        .label(reqField.getLabel())
                        .maxLength(reqField.getMaxLength())
                        .build();
                respReqFields.add(respReqField);
            }
            response.setT(respReqFields);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

}
