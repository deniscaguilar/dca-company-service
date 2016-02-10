package com.dca.company.service;

import com.dca.company.model.entity.Company;
import com.dca.company.model.view.ListCompany;

/**
 * Created by denis on 10/02/16.
 */
public interface CompanyService {

    Company save(Company company);

    Company findById(Long id);

    ListCompany findAll();

    void delete(Long id);

}
