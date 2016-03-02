package com.dca.company.service;

import com.dca.company.model.entity.BeneficialOwner;
import com.dca.company.model.view.ListBeneficialOwner;

public interface BeneficialOwnerService {

    BeneficialOwner save(BeneficialOwner beneficialOwner);

    BeneficialOwner findById(Long id);

    ListBeneficialOwner findByCompany(Long idCompany);

    void delete(Long id);

}
