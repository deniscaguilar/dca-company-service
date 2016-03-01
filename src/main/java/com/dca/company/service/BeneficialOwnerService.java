package com.dca.company.service;

import com.dca.company.model.entity.BeneficialOwner;
import com.dca.company.model.view.ListBeneficialOwner;

/**
 * Created by denis on 10/02/16.
 */
public interface BeneficialOwnerService {

    BeneficialOwner save(BeneficialOwner beneficialOwner);

    BeneficialOwner findById(Long id);

    ListBeneficialOwner findByCompany(Long idCompany);

    void delete(Long id);

}
