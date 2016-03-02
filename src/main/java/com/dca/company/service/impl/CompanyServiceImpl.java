package com.dca.company.service.impl;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Company;
import com.dca.company.model.repository.CompanyRepository;
import com.dca.company.model.view.ListCompany;
import com.dca.company.service.CompanyService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        log.info("Saving company: {}", company);

        return companyRepository.saveAndFlush(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Company findById(Long id) {
        log.info("Finding company by id {}", id);

        Company company = companyRepository.findOne(id);
        return Optional.ofNullable(company).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ListCompany findAll() {
        log.info("Finding all company");

        long count = companyRepository.count();
        return new ListCompany(Lists.newArrayList(companyRepository.findAll()), count);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting company {}", id);

        companyRepository.delete(id);
    }
}
