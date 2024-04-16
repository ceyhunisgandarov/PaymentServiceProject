package com.payment.service.service.impl;

import com.payment.service.entity.Company;
import com.payment.service.entity.CompanyImage;
import com.payment.service.entity.ServiceCategory;
import com.payment.service.entity.response.RespCompany;
import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.repository.CompanyImageRepository;
import com.payment.service.repository.CompanyRepository;
import com.payment.service.repository.CategoryRepository;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyImageRepository imageRepository;

    @Override
    public Response<List<RespCompany>> getCompaniesByCategory(String categoryName) {
        Response<List<RespCompany>> response = new Response<>();

        try {
            if (categoryName==null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            ServiceCategory category = categoryRepository.findServiceCategoryByCategoryLink(categoryName);
            if (category==null) {
                throw new MainException(ExceptionConstant.CATEGORY_NOT_FOUND, "Category not found");
            }
            List<Company> companyList = companyRepository.findAllByCategory(category);
            if (companyList ==null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Bank not found");
            }
            List<RespCompany> respCompanies = new ArrayList<>() {};
            for (Company company : companyList) {
                System.out.println(company.getName());
                CompanyImage image = imageRepository.findCompanyImageByCompany(company);
                RespCompany respCompany = RespCompany.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .companyLink(company.getCompanyLink())
                        .imagePath(image.getGetPath())
                        .category(company.getCategory().getCategoryLink())
                        .build();
                respCompanies.add(respCompany);
            }
            response.setT(respCompanies);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response<RespCompany> getCompanyByName(String companyName) {
        Response<RespCompany> response = new Response<>();

        try {
            if (companyName==null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Company company = companyRepository.findCompanyByCompanyLink(companyName);
            if (company==null) {
                throw new MainException(ExceptionConstant.COMPANY_NOT_FOUND, "Company not found");
            }
            CompanyImage image = imageRepository.findCompanyImageByCompany(company);
            if (image==null) {
                throw new MainException(ExceptionConstant.IMAGE_NOT_FOUND, "Image not found");
            }
            RespCompany respCompany = RespCompany.builder()
                    .name(company.getName())
                    .imagePath(image.getGetPath())
                    .category(company.getCategory().getCategoryLink())
                    .build();
            response.setT(respCompany);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }
}
