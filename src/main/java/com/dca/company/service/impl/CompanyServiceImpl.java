package com.dca.company.service.impl;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Company;
import com.dca.company.model.repository.CompanyRepository;
import com.dca.company.model.view.ListCompany;
import com.dca.company.service.CompanyService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by denis on 10/02/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        if (log.isDebugEnabled()) {
            log.debug("Save company: {}", company);
        }
        return companyRepository.saveAndFlush(company);
    }

    @Override
//TODO: CRIAR EXCEPTION
    public Company findById(Long id) {
        Company company = companyRepository.findOne(id);
        return Optional.ofNullable(company).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ListCompany findAll() {
        long count = companyRepository.count();
        return new ListCompany(Lists.newArrayList(companyRepository.findAll()), count);
    }

    @Override
    public void delete(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Delete company: {}", id);
        }
        companyRepository.delete(id);
    }
}
