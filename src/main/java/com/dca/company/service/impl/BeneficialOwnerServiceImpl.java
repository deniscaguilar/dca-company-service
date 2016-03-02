package com.dca.company.service.impl;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.BeneficialOwner;
import com.dca.company.model.entity.Company;
import com.dca.company.model.repository.BeneficialOwnerRepository;
import com.dca.company.model.view.ListBeneficialOwner;
import com.dca.company.service.BeneficialOwnerService;
import com.dca.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BeneficialOwnerServiceImpl implements BeneficialOwnerService {

    private final BeneficialOwnerRepository beneficialOwnerRepository;

    private final CompanyService companyService;

    @Autowired
    public BeneficialOwnerServiceImpl(BeneficialOwnerRepository beneficialOwnerRepository, CompanyService companyService) {
        this.beneficialOwnerRepository = beneficialOwnerRepository;
        this.companyService = companyService;
    }

    @Override
    public BeneficialOwner save(BeneficialOwner beneficialOwner) {
        log.info("Saving beneficial owner: {}", beneficialOwner);

        Company company = companyService.findById(beneficialOwner.getCompanyId());

        beneficialOwner.setCompany(company);
        return beneficialOwnerRepository.saveAndFlush(beneficialOwner);
    }

    @Override
    @Transactional(readOnly = true)
    public BeneficialOwner findById(Long id) {
        log.info("Finding beneficial owner by id {}", id);

        BeneficialOwner beneficialOwner = beneficialOwnerRepository.findOne(id);
        return Optional.ofNullable(beneficialOwner).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ListBeneficialOwner findByCompany(Long idCompany) {
        log.info("Finding beneficial owner by company {}", idCompany);

        Company company = companyService.findById(idCompany);
        Optional.ofNullable(company).orElseThrow(ResourceNotFoundException::new);

        List<BeneficialOwner> beneficialOwners = beneficialOwnerRepository.findByCompany(company);
        return new ListBeneficialOwner(beneficialOwners, beneficialOwners.size());
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting beneficial owner {}", id);

        beneficialOwnerRepository.delete(id);
    }
}
